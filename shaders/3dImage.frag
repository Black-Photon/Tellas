#version 330 core
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

void main()
{
    float ambientStrength = 0.175f;

    float brightness = min(max(dot(normalize(lightDir), Normal), 0.0f), 1 - ambientStrength);
    vec3 diffuse = vec3(brightness);

    vec3 ambient = vec3(ambientStrength);

    float angle = dot(normalize(lightDir), vec3(0.0, 1.0, 0.0));

    float horizon = bounded(angle * 10);
    diffuse *= horizon;

    FragColor = texture(utexture, TexCoords) * vec4(diffuse + ambient, 1.0f);
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