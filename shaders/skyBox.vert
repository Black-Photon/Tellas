#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec2 aTexCoords;

out vec2 TexCoords;

uniform mat4 view;
uniform mat4 projection;
uniform mat4 rotate;
uniform mat4 displace;

void main()
{
    gl_Position = projection * view * displace * rotate * vec4(aPos, 1.0);
    TexCoords = aTexCoords;
}