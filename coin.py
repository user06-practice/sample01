coin = [500, 100, 50, 10, 5, 1]
change = int(input("お釣りの金額を入力："))
for i in coin:
    print(f"{i}円玉は{change // i}個")
    change = change % i