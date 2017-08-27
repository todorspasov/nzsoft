# nzsoft
WELCOME

# revert Raspbian kernel to v4.4.50
sudo rpi-update 52241088c1da59a359110d39c1875cda56496764

# Как да конфигурираме достъп на WarMinion до Google Drive API:
1) Влезте в https://console.developers.google.com/start/api?id=drive
2) Натиснете Select Project -> + -> за име на проекта дайте WarMinion, за долните две настройки изберете Yes / Yes и натиснете Create.
3) Натиснете Select Project -> изберете WarMinion -> От лявата страна натиснете Credentials -> OAuth consent screen
4) Попълнете полето "Product name shown to users" със стойността WarMinion -> натиснете Save.
5) Изберете "Create Credentials" -> OAuth client ID -> Изберете "Other" -> Попълнете име WarMinon -> Натиснете Create -> Натиснете ОК.
6) Изберете стрелкичката за сваляне (Download JSON) на края на реда на WarMinion -> Запазете файла на работния плот.
7) Копирайте файла от работния плот. Във Eclipse, изберете папката src/main/resources и натиснете Ctrl+V.
8) Изберете файла client_secret_.....json -> десен бутон -> Refactor -> Rename. Задайте ново име client_secret.json и натиснете ОК.
