grammar Xquery;

WS  : [ \t\r\n]+ ->skip;

xq  : 'for' inClause 'where' cond 'return' returnClause
    ;

rp  : rp sep rp         #rp0
    | 'text()'          #rp1
    | TagName           #rp2
    ;

sep : '/'              #sep1
    | '//'             #sep2
    ;

path: 'doc("' FileName '")' sep rp               #docPath
    | Var sep rp                                 #varPath
    ;

inClause    : Var 'in' path                     #in0
			| Var 'in' path ',' inClause        #in1
			;

returnClause    : Var                                               #re0
                | returnClause ',' returnClause                     #re1
                | '<' TagName '>''{' returnClause '}''</' TagName '>'   #re2
                | path                                              #re3
                ;

cond    : object 'eq' object        #cond0
        | cond 'and' cond           #cond1
        ;

object  : Var               #object1
        | StringConstant    #object2
        ;

TagName : [-a-zA-Z0-9]+;
Var : '$'[_.a-zA-Z0-9]+;
StringConstant : '"'[_.a-zA-Z0-9 ]+'"';
FileName : [._a-zA-Z0-9]+;
