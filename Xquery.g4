grammar Xquery;
WS  : [ \t\r\n]+ ->skip;

ap  : 'doc('FileName')/'rp
    | 'doc('FileName')//'rp
    ;

rp  : tagName
    | '*' 
    | '.' 
    | '..' 
    | 'text()' 
    | '@' AttName
    | '(' rp ')'
    | rp '/' rp 
    | rp '/''/' rp 
    | rp '[' f ']' 
    | rp ',' rp
    ;

f   : rp 
    | rp '=' rp 
    | rp 'eq' rp 
    | rp '==' rp 
    | rp 'is' rp
    | '('f')' 
    | f 'and' f 
    | f 'or' f 
    | 'not' f
    ;

tagName : TagName;
TagName : [_.a-zA-Z0-9]+;

FileName : '"'[_.a-zA-Z0-9]+'"';

AttName : '"'[_.a-zA-Z0-9]+'"';
