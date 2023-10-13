year = int(input("请输入年份："))
month = int(input("请输入月份："))
day = int(input("请输入日期："))

days_of_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

# 判断是否是闰年
if (year % 4 == 0 and year % 100 != 0) or (year % 400 == 0):
    days_of_month[1] = 29

day_count = sum(days_of_month[:month-1]) + day
if month > 2 and days_of_month[1] == 29:
    day_count += 1

print(f"这一天是{year}年的第{day_count}天。")
