#version 330 core
const float PI = 3.14159265359;
out vec4 FragColor;

in vec2 TexCoords;
in vec3 FragPos;
in vec3 Normal;

uniform vec3 lightDir;

// Matrix translating from model view to orthographic
uniform mat4 shadowMatrix;

uniform sampler2D utexture;
uniform sampler2D shadowMap;

float mag(float i);
float bounded(float i);

float shadow(float angle);

void main()
{
    float ambientStrength = 0.175f;
//    float ambientStrength = 0.25f;

//    float angle;
//    if(uangle > 180) angle = uangle - 360;
//    else angle = uangle;
//    float brightness = min(max(1 - (mag(angle))/90, 0.0f), 1 - ambientStrength);
//    vec3 diffuse = vec3(brightness);

    float brightness = min(max(dot(normalize(lightDir), Normal), 0.0f), 1 - ambientStrength);
    vec3 diffuse = vec3(brightness);

    vec3 ambient = vec3(ambientStrength);

    float angle = dot(normalize(lightDir), vec3(0.0, 1.0, 0.0));

    float horizon = bounded(angle * 10);
    diffuse *= horizon;

    float shadow = shadow(angle);

    FragColor = texture(utexture, TexCoords) * vec4((1 - shadow) * diffuse + ambient, 1.0f);
//    FragColor = texture(utexture, TexCoords) * vec4((1 - shadow) * brightness * horizon + ambientStrength, 0.0f, 0.0f, 1.0f);
//    FragColor = vec4(lightDir, 1.0);
//    FragColor = vec4(vec3(angle), 1.0);
}

float mag(float i)
{
    if(i < 0) return -i;
    else return i;
}

float bounded(float i)
{
    return max(min(i, 1.0), 0.0);
}

float shadow(float angle)
{
    float shadowTotal = 0.0;
    vec2 texelSize = 1.0 / textureSize(shadowMap, 0);

    vec4 expectedPos4 = shadowMatrix * vec4(FragPos, 1.0);
    vec3 expectedPos = expectedPos4.xyz / expectedPos4.w;
    expectedPos = expectedPos * 0.5 + 0.5;// Translate to [0, 1]
    float expectedDepth = expectedPos.z;

    float bias;
    if (angle > 0.9) {
        bias = 0.002;
    } else if (angle > 0.7) {
        bias = 0.0010;
    } else if (angle > 0.3) {
        bias = 0.0005;
    } else if (angle > 0.10) {
        bias = 0.0010;
    } else {
        bias = 0.002;
    }

    for(int i = -1; i <= 1; i++) {
        for(int j = -1; j <= 1; j++) {
            float depthAtPos = texture(shadowMap, expectedPos.xy + vec2(i, j) * texelSize).r;

            float shadow = expectedDepth - bias > depthAtPos ? 1.0 : 0.0;

            if (expectedDepth > 1.0) shadow = 0.0;

            shadowTotal += shadow;
        }
    }

    return shadowTotal / 9;
}