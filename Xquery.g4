grammar Xquery;
WS  : [ \t\r\n]+ ->skip;

ap  : 'doc("' FileName '")' '/' rp  #ap0
    | 'doc("' FileName '")' '//' rp #ap1
    ;

rp  : TagName       #rp0
    | '*'           #rp1
    | '.'           #rp2
    | '..'          #rp3
    | 'text()'      #rp4
    | '@' AttName   #rp5
    | '(' rp ')'    #rp6
    | rp '/' rp     #rp7
    | rp '/''/' rp  #rp8
    | rp '[' f ']'  #rp9
    | rp ',' rp     #rp10
    ;

f   : rp            #f0
    | rp '=' rp     #f1
    | rp 'eq' rp    #f2
    | rp '==' rp    #f3
    | rp 'is' rp    #f4
    | '('f')'       #f5
    | f 'and' f     #f6
    | f 'or' f      #f7
    | 'not' f       #f8
    ;

xq  : Var                                               #xq0
    | StringConstant                                    #xq1
    | ap                                                #xq2
    | '(' xq ')'                                        #xq3
    | xq ',' xq                                         #xq4
    | xq '/' rp                                         #xq5
    | xq '//' rp                                        #xq6
    | '<' TagName '>''{' xq '}''<''/' TagName '>'       #xq7
    | 'for' inClause                                    #xq8
    | letClause xq                                      #xq9
    ;

inClause    : Var 'in' xq letClause? whereClause? returnClause  #in0
			| Var 'in' xq ',' inClause                          #in1
			;

letClause  : 'let' Var ':=' xq eqClause*;
eqClause    : ',' Var ':=' xq;

whereClause : 'where' cond;

returnClause    : 'return' xq;

cond    : xq '=' xq                                     #cond0
        | xq 'eq' xq                                    #cond1
        | xq '==' xq                                    #cond2
        | xq 'is' xq                                    #cond3
        | 'empty' '(' xq ')'                            #cond4
        | 'some' someInClause                           #cond5
        | '(' cond ')'                                  #cond6
        | cond 'and' cond                               #cond7
        | cond 'or' cond                                #cond8
        | 'not' cond                                    #cond9
        ;
someInClause    : Var 'in' xq 'satisfies' cond          #soin0
				| Var 'in' xq ',' someInClause          #soin1
				;

TagName : [a-zA-Z0-9]+;

Var : '$'[_.a-zA-Z0-9]+;

StringConstant : '"'[_.a-zA-Z0-9 ]+'"';

FileName : [_.a-zA-Z0-9]+;

AttName : '"'[_.a-zA-Z0-9]+'"';
