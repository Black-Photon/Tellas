#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec2 aTexCoords;
layout (location = 2) in vec3 aNormal;
layout (location = 3) in vec3 aOffset;

out vec2 TexCoords;
out vec3 FragPos;
out vec3 Normal;

uniform mat4 view;
uniform mat4 projection;

void main()
{
    // Using aOffset instead of model matrix as easier and instanced
    // Multiplied to 2 means that we can work with whole number coordinates rather than decimals
    gl_Position = projection * view * vec4(aPos * 2 + aOffset, 1.0);
    TexCoords = aTexCoords;
    FragPos = vec4(aPos * 2 + aOffset, 1.0).xyz;
    Normal = aNormal;
}