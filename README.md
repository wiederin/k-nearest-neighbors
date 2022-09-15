# KDTree Java Implementation

## KDTree

A KDTree is a binary search tree where K-Dimensional points in space (e.g. x,y,z) are stored in each node. 

### KDTree from 2Dsample.csv


Each non-leaf node in the tree divides the space into two parts (half-spaces). Each level or depth of the tree is aligned by one of the K-Dimensions (x,y or z etc), with the root starting at x followed by y and so on (looping through the dimensions continuously). A node at depth D will be A-aligned with A given by: A = D mod K (numbering the planes starting from 0 i.e. K-1). For an x-aligned node (root) the left subtree will contain all points whose coordinates in that plane are smaller than the root node. The right subtree will contain all points whose coordinates in that plane are greater or equal to that of the root node.

## KDTree Builder

Use KdTreeBuilder.java to build KDTrees from csv data in the csv folder.

KdTree tree print frames:

0-depth

┌──────────┐

│╭┄┄┄┄┄┄┄┄╮│

│┆x ┄ 1   ┆│

│╰┄┄┄┄┄┄┄┄╯│

└──────────┘

base construction:

line 1 = (┌), (─)*10, (┐)

line 2 = (│), (╭), (┄)*8, (╮), (|)

line 3 = (│), (┆), (x ), (┄), (1), ( ), (┆), (|)   -> 1 max size 

line 4 = (│), (╰), (┄)*8, (╯), (|) 

line 5 = (└), (─)*10, (┘)


line 2 - if (1) is over max size:

      line 0 add (─) * (1) size - max size at 1

      line 1 add (┄) * (1) size - max size at 1

      line 3 add (┄) * (1) size - max size at 1

      line 4 add (─) * (1) size - max size at 1

1-depth

```
┌──────────┐ 
│╭┄┄┄┄┄┄┄┄╮│
│┆x ┄ 1   ┆│
│┆  ┌─┴─┐ ┆│
│┆y 2   3 ┆│
│╰┄┄┄┄┄┄┄┄╯│
└──────────┘
```

2-depth

```
┌────────────────┐
│╭┄┄┄┄┄┄┄┄┄┄┄┄┄┄╮│
│┆x ┄┄┄┄ 1      ┆│
│┆    ┌──┴──┐   ┆│
│┆y ┄ 2     3   ┆│
│┆  ┌─┴─┐ ┌─┴─┐ ┆│
│┆z 4   5 6   7 ┆│
│╰┄┄┄┄┄┄┄┄┄┄┄┄┄┄╯│
└────────────────┘
```

3-depth

# Helpful Sources

## KdTree

- https://rosettacode.org/wiki/K-d_tree#Java

## Maven

- https://spring.io/guides/gs/maven/  

## Testing

- https://www.nathanbak.com/?p=388
- https://www.vogella.com/tutorials/JUnit/article.html 

## lib

- https://github.com/stefanbirkner/system-rules

## etc

- https://en.wikipedia.org/wiki/Box-drawing_character 

