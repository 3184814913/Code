from datetime import datetime

birth_str = input("请输入出生日期（例如：2000-01-01）：")
current_str = input("请输入当前日期（例如：2023-10-13）：")

birth_date = datetime.strptime(birth_str, "%Y-%m-%d")
current_date = datetime.strptime(current_str, "%Y-%m-%d")

age = current_date.year - birth_date.year

if (current_date.month, current_date.day) < (birth_date.month, birth_date.day):
    age -= 1

print("实足年龄为：", age)
