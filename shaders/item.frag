#version 330 core
out vec4 FragColor;

in vec2 TexCoords;
in vec3 Normal;

uniform sampler2D utexture;

void main()
{
    float brightness = dot(Normal, normalize(vec3(-0.5, 1.0, 1.5)));
    FragColor = vec4((brightness * texture(utexture, TexCoords)).rgb, 1.0);
}
