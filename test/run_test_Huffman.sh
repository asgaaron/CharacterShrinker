#!/bin/sh

rm -f fileEncodedSmallExpected.txt
rm -f fileEncodedSmallActual.txt
rm -f fileDecodedSmallActual.txt

java HuffmanTest -encode $1 $2 fileEncodedSmallActual.txt
java HuffmanTest -decode $1 fileEncodedSmallActual.txt fileDecodedSmallActual.txt

java Test -encode $1 $2 fileEncodedSmallExpected.txt

diff fileEncodedSmallExpected.txt fileEncodedSmallActual.txt
diff $2 fileDecodedSmallActual.txt
