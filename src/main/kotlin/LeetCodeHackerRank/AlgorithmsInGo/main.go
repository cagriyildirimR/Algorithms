package main

import "fmt"

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

type ListNode struct {
	Val  int
	Next *ListNode
}

func (l *ListNode) String() string {
	return fmt.Sprintf("val: %v and next: %v", l.Val, l.Next)
}

func mergeTwoLists(list1 *ListNode, list2 *ListNode) *ListNode {
	if list1 == nil || list2 == nil {
		return nil
	}
	head := &ListNode{
		Val:  0,
		Next: nil,
	}
	p := head

	for list1 != nil || list2 != nil {
		if list1 != nil {
			x := list1.Val
			if list2 != nil {
				y := list2.Val
				z := -1
				if x < y {
					z = x
					list1 = list1.Next
				} else {
					z = y
					list2 = list2.Next
				}
				p.Val = z
				p.Next = &ListNode{
					Val:  0,
					Next: nil,
				}
			} else {
				x := list1.Val
				list1 = list1.Next
				p.Val = x
				if list1 != nil {
					p.Next = &ListNode{
						Val:  0,
						Next: nil,
					}
				}
			}
		} else {
			y := list2.Val
			list2 = list2.Next
			p.Val = y

			if list2 != nil {
				p.Next = &ListNode{
					Val:  0,
					Next: nil,
				}
			}
		}
		p = p.Next
	}
	return head
}

func removeDuplicates(nums []int) int {
	fmt.Println("started")
	i := 0
	dup := make(map[int]int)
	for j := 0; j < len(nums); j++ {
		fmt.Printf("j: %v\n", j)
		if dup[nums[j]] == 0 {
			nums[i] = nums[j]
			i++
			dup[nums[j]]++
		}
	}
	return i
}

func isValid(s string) bool {
	i := 0
	//lastIndex := len(s)
	stack := make([]byte, 0)

	for j := 0; j < len(s); j++ {
		if s[j] == '[' || s[j] == '(' || s[j] == '{' {
			if len(stack) < i+1 {
				stack = append(stack, s[j])
			} else {
				stack[i] = s[j]
			}
			i++
			//fmt.Println(i)
		} else {
			if i == 0 {
				//fmt.Println("zero")
				return false
			}
			i--
			r := stack[i]
			switch s[j] {
			case ']':
				{
					//fmt.Println("case ]")
					if r != '[' {
						return false
					}
				}
			case ')':
				{
					//fmt.Println("case )")
					if r != '(' {
						//fmt.Printf("case is wrong want: ( but got %v", string(r))
						return false
					}
				}
			case '}':
				{
					//fmt.Println("case }")
					if r != '{' {
						return false
					}
				}
			}
		}

	}
	if i != 0 {
		return false
	}
	return true
}

func main() {
	fmt.Println(isValid("[](){}"))
	fmt.Println(isValid("[]){}"))
	fmt.Println(isValid("([)]"))

	//nums := []int{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}
	//fmt.Println(removeDuplicates(nums))
	//fmt.Println(nums)
	//l1 := &ListNode{
	//	Val: 1,
	//	Next: &ListNode{
	//		Val: 3,
	//		Next: &ListNode{
	//			Val:  4,
	//			Next: nil,
	//		},
	//	},
	//}
	//
	//l2 := &ListNode{
	//	Val: 1,
	//	Next: &ListNode{
	//		Val: 2,
	//		Next: &ListNode{
	//			Val:  4,
	//			Next: nil,
	//		},
	//	},
	//}
	//
	//r := mergeTwoLists(l1, l2)
	//
	//for r != nil {
	//	fmt.Println(r.Val)
	//	r = r.Next
	//}
}
