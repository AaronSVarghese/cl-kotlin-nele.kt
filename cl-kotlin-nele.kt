class Node<T>(var data: T) {
    var next: Node<T>? = null
}

class LinkedList<T> {
    var head: Node<T>? = null

    fun insertLast(data: T) {
        if (head == null) {
            head = Node(data)
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = Node(data)
        }
    }
}

fun <T> fromLast(list: LinkedList<T>, endIndex: Int): Node<T>? {
    var fast = list.head
    var slow = list.head

    // Move fast pointer endIndex steps ahead
    for (i in 0 until endIndex) {
        fast = fast?.next
    }

    // Move both pointers until fast reaches the end of the list
    while (fast?.next != null) {
        fast = fast.next
        slow = slow?.next
    }

    return slow
}

fun main() {
    val l = LinkedList<Char>()
    l.insertLast('a')
    l.insertLast('b')
    l.insertLast('c')
    l.insertLast('d')
    l.insertLast('e')

    println(fromLast(l, 0)?.data) // Should print 'e'
    println(fromLast(l, 3)?.data) // Should print 'b'
}
