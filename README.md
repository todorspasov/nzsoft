# nzsoft
WELCOME

# revert Raspbian kernel to v4.4.50
sudo rpi-update 52241088c1da59a359110d39c1875cda56496764

# ��� �� ������������� ������ �� WarMinion �� Google Drive API:
1) ������ � https://console.developers.google.com/start/api?id=drive
2) ��������� Select Project -> + -> �� ��� �� ������� ����� WarMinion, �� ������� ��� ��������� �������� Yes / Yes � ��������� Create.
3) ��������� Select Project -> �������� WarMinion -> �� ������ ������ ��������� Credentials -> OAuth consent screen
4) ��������� ������ "Product name shown to users" ��� ���������� WarMinion -> ��������� Save.
5) �������� "Create Credentials" -> OAuth client ID -> �������� "Other" -> ��������� ��� WarMinon -> ��������� Create -> ��������� ��.
6) �������� ������������ �� ������� (Download JSON) �� ���� �� ���� �� WarMinion -> �������� ����� �� �������� ����.
7) ��������� ����� �� �������� ����. ��� Eclipse, �������� ������� src/main/resources � ��������� Ctrl+V.
8) �������� ����� client_secret_.....json -> ����� ����� -> Refactor -> Rename. ������� ���� ��� client_secret.json � ��������� ��.
