input_str = input("请输入一个字符串：")
upper_str = input_str.upper()  # 将字符串中的小写字母转换为大写字母

with open("test.txt", "w") as file:
    file.write(upper_str)
print("已将转换后的字符串保存到 test.txt 文件中。")
