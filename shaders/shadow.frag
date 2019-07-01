#version 330 core
out vec4 FragColor;

in vec2 TexCoords;
in vec3 FragPos;

uniform float uangle;

// Matrix translating from model view to orthographic
uniform mat4 shadowMatrix;

uniform sampler2D utexture;
uniform sampler2D shadowMap;

float mag(float i);

void main()
{
    float ambientStrength = 0.2f;

    float angle;
    if(uangle > 180) angle = uangle - 360;
    else angle = uangle;
    float brightness = min(max(1 - (mag(angle))/90, 0.0f), 1 - ambientStrength);
    vec3 diffuse = vec3(brightness);

    vec3 ambient = vec3(ambientStrength);



    FragColor = texture(utexture, TexCoords) * vec4(diffuse + ambient, 1.0f);
}

float mag(float i)
{
    if(i < 0) return -i;
    else return i;
}