money = input()  # 获取输入值
if money[0] == '$':  # 判断货币类型
    dollar = float(money[1:])  # 获取美元数值
    rmb = dollar * 7.3245  # 美元转换为人民币
    print("美元转换为人民币后是：￥{:.2f}元".format(rmb))  # 输出结果
elif money[0] == '￥':
    rmb = float(money[1:])  # 获取人民币数值
    dollar = rmb / 7.3245  # 人民币转换为美元
    print("人民币转换为美元后是：${:.2f}美元".format(dollar))  # 输出结果
else:
    print("请输入正确格式的货币数值")  # 输入格式错误的处理
