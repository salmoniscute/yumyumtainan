package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    GamePanel gamePanel;


    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean enterPressed;
    private int storeNum = 0;
    private boolean ctrl = false;


    /////////////////////////////////////////////// constructor ////////////////////////////////////////////

    public KeyHandler(GamePanel gPanel) {
        this.gamePanel = gPanel;
    }


    //////////////////////////////////////  Override Key Input Methods /////////////////////////////////////

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        //title state
        if (gamePanel.getGameState() == gamePanel.titleState) {
            titleState(code);
        }
        //play state
        else if (gamePanel.getGameState() == gamePanel.playState) {
            playState(code);
        }
        //pause state
        else if (gamePanel.getGameState() == gamePanel.pauseState) {
            pauseState(code);
        }
        //dialogue state
        else if (gamePanel.getGameState() == gamePanel.dialogueState) {
            dialogueState(code);
        }
        //character state
        else if (gamePanel.getGameState() == gamePanel.characterState) {
            characterState(code);
        }
        // Store State
        else if (gamePanel.getGameState() == gamePanel.storeState) {
            storeState(code, storeNum);
        }
        else if(gamePanel.getGameState() == gamePanel.finishState){
            finishState(code);
            gamePanel.ui.setTitleScreenState(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_CONTROL) {
            ctrl = false;
        }
    }


    ////////////////////////////////////////////// Different Game States ///////////////////////////////////////////

    public void titleState(int code) {
        if (code == KeyEvent.VK_CONTROL) {
            ctrl = true;
        }

        if (gamePanel.ui.getTitleScreenState() == 0) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.ui.setCommandNum("-", 1);
                if (gamePanel.ui.getCommandNum() < 0) {
                    gamePanel.ui.setCommandNum(1);
                }
                gamePanel.playSE(1);
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.ui.setCommandNum("+", 1);
                if (gamePanel.ui.getCommandNum() > 1) {
                    gamePanel.ui.setCommandNum(0);
                }
                gamePanel.playSE(1);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.getCommandNum() == 0) {
                    //gamePanel.ui.setTitleScreenState()=1;
                    gamePanel.ui.setTitleScreenState(9);
                }
                if (gamePanel.ui.getCommandNum() == 1) {
                    System.exit(0);
                }
                gamePanel.playSE(1);
            }
        } else if (gamePanel.ui.getTitleScreenState() == 1) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.ui.setCommandNum("-", 1);
                if (gamePanel.ui.getCommandNum() < 0) {
                    gamePanel.ui.setCommandNum(1);
                }
                gamePanel.playSE(1);
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.ui.setCommandNum("+", 1);
                if (gamePanel.ui.getCommandNum() > 1) {
                    gamePanel.ui.setCommandNum(0);
                }
                gamePanel.playSE(1);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.getCommandNum() == 0) {
                    gamePanel.ui.setTitleScreenState(2);

                }
                if (gamePanel.ui.getCommandNum() == 1) {
                    gamePanel.ui.setTitleScreenState(9);
                    gamePanel.ui.setCommandNum(0);
                }
                gamePanel.playSE(1);
            }
        } else if (gamePanel.ui.getTitleScreenState() == 2) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.ui.setCommandNum("-", 1);
                if (gamePanel.ui.getCommandNum() < 0) {
                    gamePanel.ui.setCommandNum(1);
                }
                gamePanel.playSE(1);
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.ui.setCommandNum("+", 1);
                if (gamePanel.ui.getCommandNum() > 1) {
                    gamePanel.ui.setCommandNum(0);
                }
                gamePanel.playSE(1);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.getCommandNum() == 0) {
                    gamePanel.setGameState(gamePanel.playState);
                    gamePanel.npc[0].setDialogue();
                }
                if (gamePanel.ui.getCommandNum() == 1) {
                    gamePanel.ui.setTitleScreenState(1);
                    gamePanel.ui.setCommandNum(0);
                }
                gamePanel.playSE(1);
            }
        } else if (gamePanel.ui.getTitleScreenState() == 9) {

            /////////// Key input for player name ///////////
            inputToName(code);


            //////////////  options  ///////////////////////
            if (code == KeyEvent.VK_UP) {
                gamePanel.ui.setCommandNum("-", 1);
                if (gamePanel.ui.getCommandNum() < 0) {
                    gamePanel.ui.setCommandNum(1);
                }
                gamePanel.playSE(1);
            }
            if (code == KeyEvent.VK_DOWN) {
                gamePanel.ui.setCommandNum("+", 1);
                if (gamePanel.ui.getCommandNum() > 1) {
                    gamePanel.ui.setCommandNum(0);
                }
                gamePanel.playSE(1);
            }
            if (code == KeyEvent.VK_ENTER) {

                if (gamePanel.ui.getCommandNum() == 0) {
                    if (gamePanel.player.getPLayerName().length() == 0) {
                        gamePanel.ui.changeHeaderTo("姓名欄不能是空的喔！");
                    }
                    if (gamePanel.player.getPLayerName().length() > 0) {
                        gamePanel.ui.setTitleScreenState(1);
                    }
                }
                if (gamePanel.ui.getCommandNum() == 1) {
                    gamePanel.ui.setTitleScreenState(0);
                    gamePanel.ui.setCommandNum(0);
                }
                gamePanel.playSE(1);
            }

        }
    }

    public void finishState(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gamePanel.playSE(1);
            gamePanel.ui.setCommandNum("-", 1);
            if (gamePanel.ui.getCommandNum() < 0) {
                gamePanel.ui.setCommandNum(1);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gamePanel.playSE(1);
            gamePanel.ui.setCommandNum("+", 1);
            if (gamePanel.ui.getCommandNum() > 1) {
                gamePanel.ui.setCommandNum(0);
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.playSE(1);
            if (gamePanel.ui.getCommandNum() == 0) {
               gamePanel.setGameState(0);
               gamePanel.reStart();
            }
            if (gamePanel.ui.getCommandNum() == 1) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_P) {
            gamePanel.setGameState(gamePanel.pauseState);
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (code == KeyEvent.VK_K) {
            gamePanel.player.setAttacking(true);

        }
        if (code == KeyEvent.VK_C) {
            gamePanel.setGameState(gamePanel.characterState);
            gamePanel.playSE(1);
        }


    }


    public void pauseState(int code) {

        if (code == KeyEvent.VK_P) {
            gamePanel.setGameState(gamePanel.playState);
            gamePanel.playSE(1);
        }

    }

    public void dialogueState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            gamePanel.playSE(1);
            gamePanel.setGameState(gamePanel.playState);
        }
    }

    public void characterState(int code) {

        if (code == KeyEvent.VK_C) {
            gamePanel.setGameState(gamePanel.playState);
            gamePanel.playSE(1);
        }

    }

    public void storeState(int code, int i) {

        if (gamePanel.ui.getStoreScreenState() == 0) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.ui.setCommandNum("-", 1);
                if (gamePanel.ui.getCommandNum() < 0) {
                    gamePanel.ui.setCommandNum(1);
                }
                gamePanel.playSE(1);
            }

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.ui.setCommandNum("+", 1);
                if (gamePanel.ui.getCommandNum() > 1) {
                    gamePanel.ui.setCommandNum(0);
                }
                gamePanel.playSE(1);
            }

            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.getCommandNum() == 0) {
                    gamePanel.ui.setStoreScreenState(1);
                    gamePanel.player.setPlayerMoney("-", gamePanel.store[i].getCost());
                    gamePanel.player.setStoreCount("+", 1);
                    gamePanel.playSE(6); // cashier sound
                }
                if (gamePanel.ui.getCommandNum() == 1) {
                    gamePanel.setGameState(gamePanel.playState);
                    gamePanel.ui.setCommandNum(0);
                    gamePanel.playSE(5); // close door sound
                }
            }

        } else if (gamePanel.ui.getStoreScreenState() == 1) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.playState);
                gamePanel.ui.setCommandNum(0);
                gamePanel.ui.setStoreScreenState(0);
                gamePanel.playSE(5); // close door sound
            }
        }

    }


    ///////////////////// Fake TextField Iput Methods //////////////////
    private void inputToName(int code) {

        switch (code) {
            case KeyEvent.VK_A -> gamePanel.player.addPlayerName("a");
            case KeyEvent.VK_B -> gamePanel.player.addPlayerName("b");
            case KeyEvent.VK_C -> gamePanel.player.addPlayerName("c");
            case KeyEvent.VK_D -> gamePanel.player.addPlayerName("d");
            case KeyEvent.VK_E -> gamePanel.player.addPlayerName("e");
            case KeyEvent.VK_F -> gamePanel.player.addPlayerName("f");
            case KeyEvent.VK_G -> gamePanel.player.addPlayerName("g");
            case KeyEvent.VK_H -> gamePanel.player.addPlayerName("h");
            case KeyEvent.VK_I -> gamePanel.player.addPlayerName("i");
            case KeyEvent.VK_J -> gamePanel.player.addPlayerName("j");
            case KeyEvent.VK_K -> gamePanel.player.addPlayerName("k");
            case KeyEvent.VK_L -> gamePanel.player.addPlayerName("l");
            case KeyEvent.VK_M -> gamePanel.player.addPlayerName("m");
            case KeyEvent.VK_N -> gamePanel.player.addPlayerName("n");
            case KeyEvent.VK_O -> gamePanel.player.addPlayerName("o");
            case KeyEvent.VK_P -> gamePanel.player.addPlayerName("p");
            case KeyEvent.VK_Q -> gamePanel.player.addPlayerName("q");
            case KeyEvent.VK_R -> gamePanel.player.addPlayerName("r");
            case KeyEvent.VK_S -> gamePanel.player.addPlayerName("s");
            case KeyEvent.VK_T -> gamePanel.player.addPlayerName("t");
            case KeyEvent.VK_U -> gamePanel.player.addPlayerName("u");
            case KeyEvent.VK_V -> gamePanel.player.addPlayerName("v");
            case KeyEvent.VK_W -> gamePanel.player.addPlayerName("w");
            case KeyEvent.VK_X -> gamePanel.player.addPlayerName("x");
            case KeyEvent.VK_Y -> gamePanel.player.addPlayerName("y");
            case KeyEvent.VK_Z -> gamePanel.player.addPlayerName("z");
            case KeyEvent.VK_SPACE -> gamePanel.player.addPlayerName(" ");
            case KeyEvent.VK_0 -> gamePanel.player.addPlayerName("0");
            case KeyEvent.VK_1 -> gamePanel.player.addPlayerName("1");
            case KeyEvent.VK_2 -> gamePanel.player.addPlayerName("2");
            case KeyEvent.VK_3 -> gamePanel.player.addPlayerName("3");
            case KeyEvent.VK_4 -> gamePanel.player.addPlayerName("4");
            case KeyEvent.VK_5 -> gamePanel.player.addPlayerName("5");
            case KeyEvent.VK_6 -> gamePanel.player.addPlayerName("6");
            case KeyEvent.VK_7 -> gamePanel.player.addPlayerName("7");
            case KeyEvent.VK_8 -> gamePanel.player.addPlayerName("8");
            case KeyEvent.VK_9 -> gamePanel.player.addPlayerName("9");
            case KeyEvent.VK_TAB -> gamePanel.player.addPlayerName("\t");
            case KeyEvent.VK_BACK_SPACE -> {
                if (ctrl) {
                    gamePanel.player.clearPlayerName();
                    break;
                }
                gamePanel.player.backSpacePlayerName();
            }
            default -> {
            }
        }
    }

    ///////////////////////////////////////////// Encapsulation ///////////////////////////////////////////

    ///////////////////////////// Pressed
    public boolean upPressed() {
        return upPressed;
    }

    public boolean downPressed() {
        return downPressed;
    }

    public boolean leftPressed() {
        return leftPressed;
    }

    public boolean rightPressed() {
        return rightPressed;
    }

    public boolean enterPressed() {
        return enterPressed;
    }

    public void setEnterPressed(boolean b) {
        enterPressed = b;
    }

    ///////////////////////////////// Store Number

    public void setStoreNum(int i) {
        storeNum = i;
    }


}
