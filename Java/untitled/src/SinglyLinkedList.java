public class SinglyLinkedList {
    private Node head;//头指针

    //节点类
    private static class Node{
        int vales;//值
        Node next;//下一个节点指针

        public Node(int vales, Node next) {
            this.vales = vales;
            this.next = next;
        }
    }

    public void addFirst(int value){
        //链表为空
        head = new Node(value,null);
        //链表非空
        head = new Node(value,head);
    }
}
