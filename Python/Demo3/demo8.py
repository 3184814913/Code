from datetime import datetime


def time_difference(time1, time2):
    # 将输入的日期时间字符串转换为 datetime 对象
    dt1 = datetime.strptime(time1, "%Y-%m-%d %H:%M:%S")
    dt2 = datetime.strptime(time2, "%Y-%m-%d %H:%M:%S")

    # 计算两个日期时间对象之间的差值
    difference = dt2 - dt1

    # 计算天数、小时数和分钟数差异
    days = difference.days
    hours = difference.seconds // 3600
    minutes = (difference.seconds % 3600) // 60

    return days, hours, minutes


# 示例用法
time1 = "2022-10-01 12:00:00"
time2 = "2022-10-14 15:30:10"
print(time_difference(time1, time2))