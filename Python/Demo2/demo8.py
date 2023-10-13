text = "Hello World!"
new_text = ""
for char in text:
    if char.isupper():
        new_text += char.lower()
    elif char.islower():
        new_text += char.upper()
    else:
        new_text += char
print(new_text)
