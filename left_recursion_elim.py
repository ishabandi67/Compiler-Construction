file = open("Input.txt","r")
print 'Left Recursion CFG: '
nt = []
t=[]
for line in file:
    print line
    line = line.strip()
    x,y = line.split('->')
    nt.append(x)
    t.append(y.split('|'))
    
i=0


for x in nt:
    #print x
    for y in t[nt.index(x)]:
        #print y
        if y[0].isupper():
            if y[0] != x: 	
                for z in t[nt.index(y[0])]:
                    #print z
                    if z[0].isupper() and z[0] == x:
                        str = z[1:]
                        t[nt.index(y[0])].remove(z)
                        for m in t[nt.index(x)]:
                            #print m+str
                            t[nt.index(y[0])].append(m+str)
          
		
print 
print 'After Indirect Recursion Removal'
i=0
for x in nt:
    print 
    print x + '->',
    for y in t[i]:
        print y + '|',
    i+=1
i=0
flag = 0
d={}
print 
for x in nt:
    id = nt.index(x)
    for y in t[id]:    
        if y[0]==x:
            flag=1
            s= x +'\''
            if s not in d:
                d[s]=['&']
            d[s].append(y[1:]+s)
            
        else:
            if x not in d:
                d[x]=[]
            if flag==1:
                d[x].append(y+x+'\'')
            else:
                d[x].append(y)
    flag = 0
i=0
print
print 'After Direct Recursion Removal'
for x in d:
    print x + '->',
    for y in d[x]:
        print y + '|',
    print 

/*Input 1: in.txt
S->Aa|b
A->Ac|Sd|a
*/

/*Input 2: input.txt

E->E+T|T
T->T*F|F
F->(E)|id

*/
