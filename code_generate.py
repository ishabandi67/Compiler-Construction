print 'This program assumes there are only two registers and works for three adress code statements as inputs.'
print 'For the condition where no registers are empty and no operands are in the register, the register which is used first and has not been used since is emptied by using a counter s'
i = open("ip.txt","r")
o = open("op_gen.txt","w")
ad = []
s=[0,0]
dc = {'+': 'ADD','-': 'SUB','*': 'MUL','/': 'DIV'}
t=[]
print ad
print t
for line in i:
    #print line
    line = line.strip()
    x,y = line.split('=')
    t = y.split(' ')
    
    #print x
    #print t
    #print ad
    if t[0] in ad and t[2] in ad:
        #print 'bth'
        o.write(dc[t[1]] + " R" + str(ad.index(t[0])) + ", R" + str(ad.index(t[2])))
        o.write('\n')
        ind=ad.index(t[0])
        ad[ind] = x
        s[ind] = 100;
        if ind == 0:
            s[1]= s[1]-10
        else:
            s[0] = s[1] -10
        
    elif t[0] in ad:
        #print 't0'
        o.write(dc[t[1]] + "  " + t[2]+ ",R" +str(ad.index(t[0])))
        
        o.write('\n')
        ind=ad.index(t[0])
        ad[ind] = x
        s[ind] = 100;
        if ind == 0:
            s[1]= s[1]-10
        else:
            s[0] = s[1] -10
    elif t[2] in ad:
         #print 't1'
         o.write(dc[t[1]] + " R" + str(ad.index(t[2])) + "," + t[0])
         
         o.write('\n')
         ind=ad.index(t[2])
         ad[ind] = x
         s[ind] = 100;
         if ind == 0:
            s[1]= s[1]-10
         else:
            s[0] = s[1] -10
    else:
        if not ad :
           #print 'else 1'
           ad.append(t[0]);
           o.write("MOV " +  t[0] + ",R" + str(ad.index(t[0])))
           o.write('\n')
           o.write(dc[t[1]] + "  " + t[2]+ ",R" +str(ad.index(t[0])))
          
           o.write('\n')
           ind = ad.index(t[0])
           ad[ind] = x
           s[ind] = 100;
           if ind == 0:
               s[1]= s[1]-10
           else:
               s[0] = s[1] -10
        elif ad:
            if len(ad) < 2:
                if t[2] not in ad:
                    ad.append(t[2])
                    o.write("MOV " +  t[2] + ",R" + str(ad.index(t[2])))
                    o.write('\n')
                    o.write(dc[t[1]] + "  " + t[0]+ ",R" +str(ad.index(t[2])))
                   
                    o.write('\n')
                    ind=ad.index(t[2])
                    ad[ind] = x
                    s[ind] = 100;
                    if ind == 0:
                        s[1]= s[1]-10
                    else:
                        s[0] = s[1] -10
                    
                    
                elif t[0] not in ad:
                    ad.append(t[0])
                    o.write("MOV " +  t[0] + ",R" + str(ad.index(t[0])))
                    o.write('\n')
                    o.write(dc[t[1]] + "  " + t[2]+ ",R" +str(ad.index(t[0])))
                   
                    o.write('\n')
                    
                    ind=ad.index(t[0])
                    ad[ind] = x
                    s[ind] = 1000;
                    if ind == 0:
                        s[1]= s[1]-100
                    else:
                        s[0] = s[1] -100
                    
            else:
                if t[2] not in ad:
                    c+=1;
                    z=min[s[0],s[1]]
                    o.write("MOV " +  ",R" + str(z) + "x" + str(c))
                    o.write("MOV " + t[2] + ",R" + str(z))
                    ad[z]= x
                    s[z] = 1000
                    if z == 0:
                        s[1]= s[1]-100
                    else:
                        s[0] = s[1] -100
                    o.write(dc[t[1]] + "  " + t[2]+ ",R" +str(ad.index(t[0])))
                    ad[ad.index(t[0])] = x
                    o.write('\n')
                    
                    ind=ad.index(t[0])
                    ad[ind] = x
                    s[ind] = 1000;
                    if ind == 0:
                        s[1]= s[1]-100
                    else:
                        s[0] = s[1] -100
                if t[0] not in ad:
                    c+=1;
                    z=min[s[0],s[1]]
                    o.write("MOV " +  ",R" + str(z) + "x" + str(c))
                    o.write("MOV " + t[0] + ",R" + str(z))
                    ad[z]= x
                    s[z] = 1000
                    if z == 0:
                        s[1]= s[1]-100
                    else:
                        s[0] = s[1] -100
                    o.write(dc[t[1]] + "  " + t[2]+ ",R" +str(ad.index(t[0])))
                    
                    o.write('\n')
                    
                    ind=ad.index(t[0])
                    ad[ind] = x
                    s[ind] = 1000;
                    if ind == 0:
                        s[1]= s[1]-100
                    else:
                        s[0] = s[1] -100
    print ad
    print t
            
#print ad
            
print ad
print t
o.write("MOV R" + str(ind) + "," + x)
o.close();
i.close();

/*Input: ip.txt
t=a - b
u=a - c
v=t + u
d=v + u
*/
    
        

