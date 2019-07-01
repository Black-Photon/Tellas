#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec2 aTexCoords;

out vec2 TexCoords;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main()
{
    // Multiplied to 2 means that we can work with whole number coordinates rather than decimals
    gl_Position = projection * view * model * vec4(aPos * 2, 1.0);
    TexCoords = aTexCoords;
    FragPos = model * vec4(aPos * 2, 1.0);
}