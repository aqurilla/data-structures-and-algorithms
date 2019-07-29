class Node:
    def __init__(self, val):
        self.val = val
        self.next = None
        
class SinglyLinkedList:
    def __init__(self):
        self.length = 0
        self.head = None
        self.tail = None
        
    def push(self, val):
        newNode = Node(val)
        if not self.head:
            self.head = newNode
            self.tail = newNode
        else:
            self.tail.next = newNode
            self.tail = newNode
        self.length += 1
        return
    
    def pop(self):
        if self.length == 0:
            print('Empty list!')
            return None
        
        pre = None
        k = self.head
        while k.next:
            pre = k
            k = k.next
        self.tail = pre
        if self.tail: self.tail.next = None
        self.length -= 1
        if self.length == 0: self.head = None
        print(f'Popping {k.val}')
        return k.val
    
    def shift(self):
        # Remove value from head
        if self.length == 0:
            print('Empty list!')
            return None
        tmp = self.head
        print(f'Shifting {tmp.val}')
        self.head = self.head.next
        self.length -= 1
        if self.length == 0: self.tail = None
        return tmp.val
    
    def unshift(self, val):
        # Add value at head
        newNode = Node(val)
        print(f'Unshifting {val}')
        if self.length == 0:
            self.head = newNode
            self.tail = newNode
        else:
            newNode.next = self.head
            self.head = newNode
        self.length += 1
        return
    
    def getter(self, idx):
        if idx < 0 or idx >= self.length:
            return None
        counter = 0
        tmp = self.head
        while counter != idx:
            tmp = tmp.next
            counter += 1
        print(f'Got {tmp.val}')
        return tmp
    
    def setter(self, idx, val):
        # Sets value of existing node
        tmp = self.getter(idx)
        if tmp == None:
            print(f'{idx}: Invalid index')
            return False
        else:
            print(f'Changing idx: {idx} to val: {val}')
            tmp.val = val
            return True
        
    def insert(self, idx, val):
        if idx < 0 or idx > self.length:
            return False
        if idx == 0:
            self.unshift(val)
            return True
        if idx == self.length:
            self.push(val)
            return True
        else:
            tmp = self.getter(idx-1)
            newNode = Node(val)
            newNode.next = tmp.next
            tmp.next = newNode
            self.length += 1
            return True
    
    def remove(self, idx, val):
        if idx < 0 or idx >= self.length:
            return None
        if idx == 0:
            self.shift(val)
            return True
        if idx == self.length-1:
            self.pop(val)
            return True
        else:
            tmp = self.getter(idx-1)
            removedNode = tmp.next
            tmp.next = removedNode.next
            self.length -= 1
            return removedNode
    
    def traverseList(self):
        # func for traversing the list
        k = self.head
        while k:
            print(k.val)
            k = k.next
        return
            
    
# def push(self, val):
#     newNode = Node(val)
#     newNode.next = self.head
#     if self.length == 0:
#         self.tail = newNode
#     self.head = newNode
#     self.length += 1
        
a = SinglyLinkedList()

a.push('hi')
print(a.tail.val)
print(a.length)
a.push('bye')
print(a.tail.val)
print(a.length)

print(a.traverseList())
