import java.util.Scanner;
public class tributarono {

age = int(input("�Cu�l es tu edad? "))
income = float(input("�Cuales son tus ingresos mensuales?"))
if age > 16 and income >= 1000:
    print("Tienes que cotizar")
else:
    print("No tienes que cotizar")


age = int(input("�Cu�l es tu edad? "))
income = float(input("�Cuales son tus ingresos mensuales?"))
if age <= 16 or income < 1000:
    print("No tienes que cotizar")
else:
    print("Tienes que cotizar")