a = float(input("请输入第一条边长："))
b = float(input("请输入第二条边长："))
c = float(input("请输入第三条边长："))

if a + b > c and a + c > b and b + c > a:
    if a == b or b == c or a == c:
        if a == b == c:
            print("该三角形是等边三角形")
        else:
            print("该三角形是等腰三角形")

        if a ** 2 + b ** 2 == c ** 2 or a ** 2 + c ** 2 == b ** 2 or b ** 2 + c ** 2 == a ** 2:
            print("该三角形是直角三角形")

    else:
        print("该三角形是普通三角形")
else:
    print("不能组成三角形")
