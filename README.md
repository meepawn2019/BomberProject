# BomberProject

https://github.com/littleha233/Crazy-Arcade/tree/master/Crazy-Arcade/PaoPaoTang/Resources/Pic
public void drawObject(Graphics2D g2) { //chỉ vẽ nổ phải
        int i=1;
        while(i<=size&&isDraw){
            g2.drawImage(img_right, x+45*i,y, null);
            if(isImpactBrick(TEST.listObject,x+45*i,y, i)){
                isDraw=false;
                break;
            }
            i++;
            
        }
        //g2.drawImage(img_left, x-45*(size+1), y,null);
          //g2.drawImage(img_right, x+45*size, y,null);
//        g2.drawImage(img_up, x, y+45*size,null);
//        g2.drawImage(img_down, x, y-45*size,null);
 
    }
 public boolean isImpactBrick(ArrayList<OBJECT> list,int x,int y,int i){ //xét xem có phải là Brick hay ko?
        for(OBJECT object : list){//duyệt list
            if(object instanceof Brick){//down casting
                if(((Brick) object).x<=this.x+45*i&&((Brick) object).y==this.y) {//biểu thức boolean xem có xóa hay ko
                    removeBrick(list, (Brick) object);//xóa
                    return true;//đã xóa
                }
            }
        }
        return false;//chưa xóa
    }
    public void removeBrick(ArrayList<OBJECT> list,Brick brick){//hàm xóa
        Iterator<OBJECT> ite = list.iterator();//dùng Iterator để xóa, nếu dùng list.move thì sẽ ko đấm dồn lên mà phần tử đó sẽ trở thành null và lần duyệt tiếp theo sẽ sai 
        while(ite.hasNext()){
            OBJECT object = ite.next();//ite.next sẽ trả về đối tượng tiếp theo của list
            if(object instanceof Brick){
                if(object.equals(brick)){
                    ite.remove();
                    break;
                }
            }
        }
    }
