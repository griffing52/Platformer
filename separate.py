text = ""
with open("original.txt", "r") as f:
    text = f.read()

allImports = text[:text.find("\n\n")].lstrip().split("\n")

imports = text[:text.find("\n\n")-1].split(";\n")
imports = [x[::-1] for x in imports]
imports = [x[:x.find(".")][::-1] for x in imports]

text = text[text.find("\n\n")+2:]
while text.find("class") != -1:
    name = text[text.find("class ")+6:text.find(" {")]
    if (name.find(" extends") != -1):
        name = name[:name.find(" extends")]
    res = "public " + text[:text.find("\n}")+2]

    # Add imports to top
    addition = ""
    for i in range(0, len(imports)):
        if (res.find(imports[i]) != -1):
            addition+=allImports[i] + "\n"

    if addition != "":
        addition += "\n" 
    
    res = addition + res

    with open(""+name+".pde", "w+") as n:
        n.write(res)

    text = text[text.find("\n}")+4:]
    
with open("DoodleJumpTest.pde", "w+") as s:
    s.write(text)