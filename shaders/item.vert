#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec2 aTexCoords;
layout (location = 2) in vec3 aNormal;

out vec2 TexCoords;
out vec3 Normal;

uniform vec2 position;
uniform vec2 screen;
uniform mat4 model;

void main()
{
    vec4 modelPos = model * vec4(aPos, 1.0);
    modelPos = modelPos / vec4(screen, 1.0, 1.0);
    gl_Position = vec4(modelPos.xy + 2 * vec2(position / screen) - vec2(1.0, 1.0), 0.0, 1.0);
    TexCoords = aTexCoords;
    Normal = aNormal;
}