#!/usr/bin/env python3

"""
Converts stdin from '|||' separators
to word cleaned output
"""


import sys


if __name__ == '__main__':
    if len(sys.argv) > 1:
        stream = open(sys.argv[1], 'r')
    else:
        stream = sys.stdin
    for line in stream:
        values = line.split('|||')
        word1 = values[1].strip()
        word2 = values[2].strip()
        print('\t'.join([word1, word2]))
    stream.close()
