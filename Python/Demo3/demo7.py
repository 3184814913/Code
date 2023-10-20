from datetime import datetime

def time_difference(time1, time2):
    format = "%H:%M"
    t1 = datetime.strptime(time1, format)
    t2 = datetime.strptime(time2, format)
    diff = t2 - t1
    days = diff.days
    hours = diff.seconds // 3600
    minutes = (diff.seconds % 3600) // 60
    return days, hours, minutes

time1 = "12:30"
time2 = "15:45"
days, hours, minutes = time_difference(time1, time2)
print("两个时间之间的差异为：{}天{}小时{}分钟。".format(days, hours, minutes))
