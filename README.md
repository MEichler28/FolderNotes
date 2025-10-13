# FolderNotes – Android-App (in Arbeit)

FolderNotes ist eine Android-App zur **geordneten Organisation verschiedener Inhaltstypen**.  
Ziel ist es, Notizen, Bilder, Audioaufnahmen, Zeichnungen und Listen übersichtlich nach Themen zu strukturieren.

## Konzept (Elementtypen)
Jedes Element steht eigenständig, es gibt keine Anhänge innerhalb einer Notiz:
- 📄 **Notiz** – reiner Text  
- 🖼️ **Bild** – kann aufgenommen oder aus einem Ordner gewählt werden  
- 🎙️ **Audio** – direkt in der App aufgenommen  
- 🖊️ **Zeichnung** – kann direkt erstellt werden  
- ☑️ **Liste** – einfache Aufgaben- oder Punktlisten  
- 📁 **Ordner** – dient zur strukturierten Organisation der Inhalte  

## Aktueller Stand
- Grundstruktur des Projekts steht  
- **Room-Anbindung vollständig implementiert**  
- **Aktuell arbeite ich an der MainActivity und den XML-Layouts**  
- Ordner- und Elementfunktionen werden im nächsten Schritt umgesetzt  

## Nächste Schritte (Roadmap)
- [ ] Startansicht mit Ordnerübersicht  
- [ ] Darstellung einzelner Elemente als schmale Kacheln (nur Titelanzeige)  
- [ ] Editoransichten für Text, Bilder, Audio, Zeichnungen und Listen  
- [ ] „Speichern?“-Abfrage beim Verlassen  
- [ ] Aufnahme- und Auswahlfunktionen für Medien  
- [ ] Such- und Sortierfunktionen  
- [ ] Backup- und Wiederherstellungsoptionen  

## Technisches
- **Sprache:** Java  
- **IDE:** Android Studio  
- **Datenbank:** Room  
- **UI:** ConstraintLayout, RecyclerView, Material Components  
- **Speicherorte:** App-interner Speicher für Medien  
- **Berechtigungen:** Kamera, Mikrofon, Medienzugriff (Runtime Permissions)  

---

*Dieses Repository wird laufend aktualisiert.*
