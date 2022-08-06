# KDTree Java Implementation

## KDTree

A KDTree is a binary search tree where K-Dimensional points in space (e.g. x,y,z) are stored in each node. 

### KDTree from 2Dsample.csv


Each non-leaf node in the tree divides the space into two parts (half-spaces). Each level or depth of the tree is aligned by one of the K-Dimensions (x,y or z etc), with the root starting at x followed by y and so on (looping through the dimensions continuously). A node at depth D will be A-aligned with A given by: A = D mod K (numbering the planes starting from 0 i.e. K-1). For an x-aligned node (root) the left subtree will contain all points whose coordinates in that plane are smaller than the root node. The right subtree will contain all points whose coordinates in that plane are greater or equal to that of the root node.

## KDTree Builder

Use KdTreeBuilder.java to build KDTrees from csv data in the csv folder.

KdTree

1-depth

0     9.0,1.0
       /    \  
1 7.0,8.0   13.0,15.0

2-depth

0              9.0,1.0
          ________|________
         /                 \  
1     7.0,8.0            13.0,15.0
      /     \             /     \
2  5.0,6.0 6.0,12.0  17.0,15.0 10.0,19.0

3-depth

0              9.0,1.0
          ________|________
         /                 \  
1     7.0,8.0            13.0,15.0
      /     \             /     \
2  5.0,6.0 6.0,12.0  17.0,15.0 10.0,19.0
    /  \     /  \       /  \      /  \
3 3.0,6.0

# Helpful Sources

## KdTree

- https://rosettacode.org/wiki/K-d_tree#Java
- https://www.baeldung.com/java-print-binary-tree-diagram 

## Maven

- https://spring.io/guides/gs/maven/  

## Testing

- https://www.nathanbak.com/?p=388
- https://www.vogella.com/tutorials/JUnit/article.html 

## lib

- https://github.com/stefanbirkner/system-rules
