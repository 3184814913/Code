import math
x = eval(input())
if x<2:
    y = 3*x**2+1
elif 2<=x<10:
    y = math.sqrt(2*x-1)
elif x>=10:
    y = 1/(x+1)
print("{:.2f}".format(y))