max_attempts = 3
password = "123456"
attempts = 0

while attempts < max_attempts:
    user_input = input("请输入密码：")
    if user_input == password:
        print("登录成功！")
        break
    else:
        attempts += 1
        print("密码错误，请重试！")

if attempts == max_attempts:
    print("登录失败，已达到最大尝试次数！")
