# Tugas Kecil 3 Strategi Algoritma 

## Daftar Isi 
- [Tugas kecil3 Strategi Algoritma](#tugas-kecil-3-strategi-algoritma)
    - [Daftar isi](#daftar-isi)
    - [Word Ladder](#word-ladder)
    - [Deskripsi Program](#deskripsi-program)
    - [Struktur Program](#struktur-program)
    - [Menjalankan Program](#menjalankan-program)

## Word Ladder
Made by Indraswara Galih Jayanegara, 13522119, for STIMA *course*

## Deskripsi Program
Word Ladder adalah permainan untuk menebak kata dari kata start sampai dengan kata tujuan. 
Projek ini membuat suatu algoritma dengan pendekatan Route Planning UCS, A*, dan Greedy Best First Search untuk menyelesaikan Word Ladder. 


## Struktur Program
```
| bin
| doc
|   |
| test
| src
|   | Algorithm 
|   | Data
|   | GUI
|   | Util
|   | Main
|
| compile.bat
| compile.sh
| run.bat
| run.sh

```
# Menjalankan Program 

## *compile & running*

## *compile* 

### Windows
pastikan pada root folder
```
compile
```
### WSL
jalankan perintah berikut terlebih dahulu
```
chmod +x compile.sh
```
lalu
```
./compile.sh
```
## *run*
### Windows
jalankan perintah berikut
```
run
```
### WSL
jalankan perintah berikut terlebih dahulu
```
chmod +x run.sh
```
lalu
```
./run.sh
```

# Alternatif Menjalankan Program
alternatif menjalankan program pada *root directory*

## *Compile & Run*

### *windows*
jalankan perintah 
```
g++ -std=c++20 -o bin/app src/main.cpp && bin\app
```

### *WSL*
jalankan perintah 
```
g++ -std=c++20 -o ./bin/app ./src/main.cpp && ./bin/app
```

## *Run*
### *Windows*
```
bin\app
```
### *WSL*
```
./bin/app
```