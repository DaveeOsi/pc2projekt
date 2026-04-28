# pc2projekt
# Databázový systém zaměstnanců

Tento projekt je konzolová aplikace v Javě simulující databázi zaměstnanců technologické firmy.
Se zaměřením na objektově orientovaného programování (OOP).

## Funkcionality

Systém umožňuje komplexní správu zaměstnanců a jejich vzájemných vazeb:

* **Správa zaměstnanců:** Přidávání nových zaměstnanců s automaticky generovaným ID a jejich kompletní odebírání (včetně smazání všech vazeb na kolegy).
* **Evidence spolupráce:** Možnost propojit dva zaměstnance a nastavit úroveň jejich spolupráce (Špatná, Průměrná, Dobrá).
* **Speciální dovednosti:** Každý zaměstnanec je zařazen do pevné skupiny s unikátní schopností:
* *Datový analytik:* Najde kolegu, se kterým má nejvíce společných spolupracovníků.
* *Bezpečnostní specialista:* Vypočítá rizikové skóre spolupráce na základě vlastního algoritmu.
* **Statistiky a výpisy:** * Vyhledání konkrétního zaměstnance podle ID.
  * Abecední výpis podle příjmení rozdělený do skupin.
  * Zobrazení počtu zaměstnanců v jednotlivých skupinách.
  * Analýza převažující kvality spolupráce a zjištění zaměstnance s nejvíce vazbami.
* **Ukládání dat:**
  * Uložení/načtení jednotlivého zaměstnance do/z binárního souboru (serializace).
  * Celková záloha databáze do SQL při ukončení programu a její automatické načtení při spuštění (program primárně běží v paměti).

## Použité technologie
* **Jazyk:** Java
* **Architektura:** Konzolová aplikace (CLI)
* **Databáze:** SQLite (prostřednictvím JDBC)
* **Datové struktury:** `HashMap`, `ArrayList`
* **Další:** Java Streams, Enum, Rozhraní (Interface), Dědičnost, Serializace objektů




