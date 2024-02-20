from ast import Delete
from cgitb import text
from ctypes.wintypes import SIZE
from faulthandler import disable
from msilib.schema import Font
from tkinter import *
from tkinter import font
from unittest import skip
board = Tk()
board.geometry('279x330')
board.maxsize(279,330)
board.minsize(279,330)

c = True # for the players to take turns and to verify there is a winner and to declare the name of the winner
winner = False
lab = Label(board,width=11)

color = Entry() # color of the lines
color.pack()
color.insert(0,'Board color')

boxcolor = Entry() # color of the spaces between the lines
boxcolor.pack()
boxcolor.insert(0,'Box color')

piname = Entry()
piname.pack()
piname.insert(0,'player one name')

picolor = Entry()
picolor.pack()
picolor.insert(0,'player one color')

piiname = Entry()
piiname.pack()
piiname.insert(0,'player two name')

piicolor = Entry()
piicolor.pack()
piicolor.insert(0,'player two color')

playeri = None # holders for players name
playerii = None

playeric = None # holders for players colors
playeriic = None

def start():
    global playeri
    global playerii
    global playeric
    global playeriic
    global bboxcolor

    blist = [b1,b2,b3,b4,b5,b6,b7,b8,b9]

    bcolor = color.get()
    playeri = piname.get()
    playerii = piiname.get()
    playeric = picolor.get()
    playeriic = piicolor.get()
    bboxcolor = boxcolor.get()

    for x in blist:
        x['bg'] = bboxcolor

    piname.pack_forget()
    piiname.pack_forget()
    color.pack_forget()
    play.pack_forget()
    picolor.pack_forget()
    piicolor.pack_forget()
    boxcolor.pack_forget()

    board.config(bg=bcolor)

    b1.grid(column=0,row=0)
    b2.grid(column=1,row=0,padx=5)
    b3.grid(column=2,row=0)
    b4.grid(column=0,row=1,pady=5)
    b5.grid(column=1,row=1,padx=5,pady=5)
    b6.grid(column=2,row=1,pady=5)
    b7.grid(column=0,row=2)
    b8.grid(column=1,row=2,padx=5)
    b9.grid(column=2,row=2)

    restart.grid(row = 3, column=2,pady=2)
    exit.grid(row = 3, column=0,pady=2)
    rrestart.grid(row = 4, column=1)

play = Button(text='Play',command=start)
play.pack()

def glowx():
    list = [b1,b2,b3,b4,b5,b6,b7,b8,b9]
    for x in list:
        if x['text'] == 'X':
            x['bg'] = 'red'
            x['disabledforeground'] = 'white'

def glowo():
    list = [b1,b2,b3,b4,b5,b6,b7,b8,b9]
    for x in list:
        if x['text'] == 'O':
            x['bg'] = 'blue'
            x['disabledforeground'] = 'white'

def click(b):
    global c
    global lab
    if c == True:
        b['text'] = 'X'
        b['disabledforeground'] = playeric
        c = False
    else:
        b['text'] = 'O'
        b['disabledforeground'] = playeriic
        c = True

    b['state'] = DISABLED
    win()
    if full() == True and winner == False:
        lab['text'] = "It's a tie"
        lab.grid(row=3,column=1)


def full():
    if b1['text']  != ' ' and b2['text']  != ' ' and b3['text']  != ' ' and b4['text']  != ' ' and b5['text']  != ' ' and b6['text']  != ' ' and b7['text']  != ' ' and b8['text']  != ' ' and b9['text']  != ' ':
        return True
    else:
        return False

def win():
    global winner
    global lab
    chwin(b1,b2,b3)
    chwin(b4,b5,b6)
    chwin(b7,b8,b9)
    chwin(b1,b4,b7)
    chwin(b2,b5,b8)
    chwin(b3,b6,b9)
    chwin(b1,b5,b9)
    chwin(b3,b5,b7)

def chwin(a,b,c):
    global winner
    if a['text'] == b['text'] == c['text'] == 'X':
        lab['text'] = f'{playeri} wins'
        lab.grid(row=3,column=1)
        winner = True
        a['disabledforeground'] = b['disabledforeground'] = c['disabledforeground'] = 'white'
        a['bg'] = b['bg'] = c['bg'] = playeric
        end()
    elif a['text'] == b['text'] == c['text'] == 'O':
        lab['text'] = f'{playerii} wins'
        lab.grid(row=3,column=1)
        winner = True
        a['disabledforeground'] = b['disabledforeground'] = c['disabledforeground'] = 'white'
        a['bg'] = b['bg'] = c['bg'] = playeriic
        end()

def rere():
    re()
    b1.grid_forget()
    b2.grid_forget()
    b3.grid_forget()
    b4.grid_forget()
    b5.grid_forget()
    b6.grid_forget()
    b7.grid_forget()
    b8.grid_forget()
    b9.grid_forget()

    restart.grid_forget()
    exit.grid_forget()
    rrestart.grid_forget()
    lab.grid_forget()

    color.pack()
    boxcolor.pack()
    piname.pack()
    picolor.pack()
    piiname.pack()
    piicolor.pack()
    play.pack()

def end():
    b1['state'] = DISABLED
    b2['state'] = DISABLED
    b3['state'] = DISABLED
    b4['state'] = DISABLED
    b5['state'] = DISABLED
    b6['state'] = DISABLED
    b7['state'] = DISABLED
    b8['state'] = DISABLED
    b9['state'] = DISABLED

def re():
    global c
    list = [b1,b2,b3,b4,b5,b6,b7,b8,b9]

    b1['state'] = NORMAL
    b2['state'] = NORMAL
    b3['state'] = NORMAL
    b4['state'] = NORMAL
    b5['state'] = NORMAL
    b6['state'] = NORMAL
    b7['state'] = NORMAL
    b8['state'] = NORMAL
    b9['state'] = NORMAL

    b1['text'] = ' '
    b2['text'] = ' '
    b3['text'] = ' '
    b4['text'] = ' '
    b5['text'] = ' '
    b6['text'] = ' '
    b7['text'] = ' '
    b8['text'] = ' '
    b9['text'] = ' '

    for x in list:
        x['bg'] = bboxcolor

    c = True
    lab.grid_remove()  

bw = 5
bh = 2
ff = font.Font(size=20)

b1 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b1),font=ff)
b2 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b2),font=ff)
b3 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b3),font=ff)
b4 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b4),font=ff)
b5 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b5),font=ff)
b6 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b6),font=ff)
b7 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b7),font=ff)
b8 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b8),font=ff)
b9 = Button(board,width=bw,text=' ',height=bh,bg='white',command=lambda: click(b9),font=ff)

restart = Button(board,width=11,height=1,text='Restart',command=re)
exit = Button(board,width=11,height=1,text='EXIT',command=board.quit)
rrestart = Button(board,width=11,height=1,text='Back',command=rere)

board.mainloop()