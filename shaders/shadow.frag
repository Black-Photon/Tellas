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

void main()
{
    float ambientStrength = 0.2f;

//    float angle;
//    if(uangle > 180) angle = uangle - 360;
//    else angle = uangle;
//    float brightness = min(max(1 - (mag(angle))/90, 0.0f), 1 - ambientStrength);
//    vec3 diffuse = vec3(brightness);

    float brightness = min(max(dot(normalize(lightDir), Normal), 0.0f), 1 - ambientStrength);
    vec3 diffuse = vec3(brightness);

    vec3 ambient = vec3(ambientStrength);

    vec4 expectedPos4 = shadowMatrix * vec4(FragPos, 1.0);
    vec3 expectedPos = expectedPos4.xyz / expectedPos4.w;
    expectedPos = expectedPos * 0.5 + 0.5; // Translate to [0, 1]
    float depthAtPos = texture(shadowMap, expectedPos.xy).r;
    float expectedDepth = expectedPos.z;

    float shadow = expectedDepth - 0.0001 > depthAtPos ? 1.0 : 0.0;

    if(expectedDepth > 1.0)
        shadow = 0.0;

    FragColor = texture(utexture, TexCoords) * vec4((1 - shadow) * diffuse + ambient, 1.0f);
//    FragColor = vec4(Normal * 0.5 + 0.5, 1.0);
}

float mag(float i)
{
    if(i < 0) return -i;
    else return i;
}