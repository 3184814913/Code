import math

x = 12
y = 10 ** -5

# (1)
result_1 = 1 + (x / math.factorial(3)) - (y / math.factorial(5))
print(result_1)

# (2)
result_2 = (2 * math.log(abs(x - y))) / (math.exp(x + y) - math.tan(y))
print(result_2)

# (3)
result_3 = ((math.sin(x) + math.cos(y)) / (x ** 2 + y ** 2)) + ((x ** y) / (x * y))
print(result_3)

# (4)
result_4 = math.exp((math.pi / 2) * x) + (math.log10(abs(x - y))) / (x + y)
print(result_4)
