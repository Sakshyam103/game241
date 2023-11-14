package Game241.Project;


    public class BinarySearchTree<K extends Comparable<K>,V> {      //k can be compared with its type
        private Node root;
        private int size;

        /**
         * Empty binary search tree.
         */
        public BinarySearchTree() {
            root = null;
            size = 0;
        }

        /*
         * Find Strategy:
         * - If the current root is null (i.e., tree is empty), return false
         * - If the target matches the current root nodes data, return true
         * - If target < current root node's data, search left subtree
         * - If target > current root node's data, search right subtree
         */

        // Externally, all we want to worry about is the thing we're looking for.
        public V find(K target) {
            return find(root, target);
        }

        // Internally, we have to keep track of the current node.
        private V find(Node currentNode, K target) {
            if (currentNode == null) return null;
            int compareResult = target.compareTo(currentNode.key);
            if (compareResult == 0) {
                return currentNode.value;
            }
            if (compareResult < 0)
                // Return the result of find in the left subtree.
                return find(currentNode.left, target);
            return find(currentNode.right, target);
        }

        /*
         * Insert strategy:
         * - If the current root is null, replace current root with a
         *      new node containing the item
         * - If the item equals the current root node's data, do nothing.
         * - If item < current root node's data, recursively insert into left subtree.
         * - If item > current root node's data, recursively insert into right subtree.
         */

        public boolean add(K key, V value) {
            int oldSize = size;
            root = add(root, key, value);
            return oldSize != size;
        }

        private Node add(Node currentNode, K key, V value) {
            if (currentNode == null) {
                size++;
                return new Node(key, value);
            }
            if (key.compareTo(currentNode.key) == 0) {
                return currentNode;
            }
            if (key.compareTo(currentNode.key) < 0) {
                // Go left, and store the left node in the left.
                currentNode.left = add(currentNode.left, key, value);
                return currentNode;
            }
            //else (item.compareTo(currentNode.data) > 0) {
            currentNode.right = add(currentNode.right, key, value);
            return currentNode;
            //}
        }

        public void remove(K key){
            root=remove(root,key);
        }

        private Node remove(Node root,K key){
            if(root==null){
                return null;
            }if(key.compareTo(root.key)<0){
                root.left=remove(root.left,key);
            }if (key.compareTo(root.key)>0){
                root.right=remove(root.right,key);
            }if (key.compareTo(root.key)==0){
                if (root.left==null && root.right==null){
                    return null;
                }else if(root.left!=null && root.right==null){
                    return root.left;
                }else if(root.left==null){
                    return root.right;
                }
                Node newNode= PreviousRoom(root);
                remove(newNode.key);
                newNode.left=root.left;
                newNode.right=root.right;
                root=newNode;
            }
            return root;
        }

        private Node PreviousRoom(Node room){
            Node temp=room.left;
            while(temp.right!=null){
                temp=temp.right;
            }
            return temp;
        }

        public String toString() {
            if (root == null) return "null";
            return root.toString();
        }


        public class Node {
            // The data stored in the node
           private K key;
            private  V value;
            // The root of the left subtree
            Node left;
            // The root of the right subtree
            Node right;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
                left = right = null;
            }

            public String toString() {
                return "(" + left + " - " + key + " - " + right + ")";
                //return data.toString();
            }

            public K getKey(){
                return key ;
            }
        }


        public void Traverse(){
            traverse(root);
        }
        public void traverse(Node node) {
            if (node.left != null) {
                traverse(node.left);
            }
            System.out.println(node.value);
            if(node.right!=null){
                traverse(node.right);
            }
        }
        public Node getroot(){
            return root;
        }

        }



