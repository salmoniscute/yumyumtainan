package main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    GamePanel gamePanel;


    public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed, attackPressed;
    public int storeNum = 0;


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

        if(code == KeyEvent.VK_ESCAPE){ System.exit(0); }

        //title state
        if (gamePanel.gameState == gamePanel.titleState) {
            titleState(code);
        }
        //play state
        else if (gamePanel.gameState == gamePanel.playState) {
            playState(code);
        }
        //pause state
        else if (gamePanel.gameState == gamePanel.pauseState) {
            pauseState(code);
        }
        //dialogue state
        else if (gamePanel.gameState == gamePanel.dialogueState) {
            dialogueState(code);
        }
        //character state
        else if (gamePanel.gameState == gamePanel.characterState) {
            characterState(code);
        }
        // Store State
        else if (gamePanel.gameState == gamePanel.storeState) {
            storeState(code, storeNum);
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
    }


    ////////////////////////////////////////////// Different Gamestates ///////////////////////////////////////////

    public void titleState(int code) {

        if (gamePanel.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.ui.commandNum--;
                if (gamePanel.ui.commandNum < 0) {
                    gamePanel.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNum++;
                if (gamePanel.ui.commandNum > 1) {
                    gamePanel.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandNum == 0) {
                    //gamePanel.ui.titleScreenState=1;
                    gamePanel.ui.titleScreenState = 9;
                }
                if (gamePanel.ui.commandNum == 1) {
                    System.exit(0);
                }

            }
        } else if (gamePanel.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.ui.commandNum--;
                if (gamePanel.ui.commandNum < 0) {
                    gamePanel.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNum++;
                if (gamePanel.ui.commandNum > 1) {
                    gamePanel.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandNum == 0) {
                    gamePanel.ui.titleScreenState = 2;

                }
                if (gamePanel.ui.commandNum == 1) {
                    gamePanel.ui.titleScreenState = 9;
                    gamePanel.ui.commandNum = 0;
                }
            }
        } else if (gamePanel.ui.titleScreenState == 2) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.ui.commandNum--;
                if (gamePanel.ui.commandNum < 0) {
                    gamePanel.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNum++;
                if (gamePanel.ui.commandNum > 1) {
                    gamePanel.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandNum == 0) {
                    gamePanel.gameState = gamePanel.playState;
                    gamePanel.npc[0].setDialogue();
                }
                if (gamePanel.ui.commandNum == 1) {
                    gamePanel.ui.titleScreenState = 1;

                    gamePanel.ui.commandNum = 0;
                }
            }
        } else if (gamePanel.ui.titleScreenState == 9) {

            /////////// Key input for player name ///////////
            inputToName(code);


            //////////////  options  ///////////////////////
            if (code == KeyEvent.VK_UP) {
                gamePanel.ui.commandNum--;
                if (gamePanel.ui.commandNum < 0) {
                    gamePanel.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNum++;
                if (gamePanel.ui.commandNum > 1) {
                    gamePanel.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {

                if (gamePanel.ui.commandNum == 0) {
                    if (gamePanel.player.getPlayerName().length() == 0) {
                        gamePanel.ui.changeHeaderTo("姓名欄不能是空的喔！");
                    }
                    if (gamePanel.player.getPlayerName().length() > 0) {
                        gamePanel.ui.titleScreenState = 1;
                    }
                }

                if (gamePanel.ui.commandNum == 1) {
                    gamePanel.ui.titleScreenState = 0;

                    gamePanel.ui.commandNum = 0;
                }
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
            gamePanel.gameState = gamePanel.pauseState;

        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;

        }
        if (code == KeyEvent.VK_K) {
            attackPressed = true;
            gamePanel.player.attacking = true;

        }
        if (code == KeyEvent.VK_C) {
            gamePanel.gameState = gamePanel.characterState;

        }


    }


    public void pauseState(int code) {

        if (code == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.playState;

        }

    }

    public void dialogueState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void characterState(int code) {

        if (code == KeyEvent.VK_C) {
            gamePanel.gameState = gamePanel.playState;

        }

    }

    public void storeState(int code, int i) {

        if (gamePanel.ui.storeScreenState == 0) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gamePanel.ui.commandNum--;
                if (gamePanel.ui.commandNum < 0) {
                    gamePanel.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNum++;
                if (gamePanel.ui.commandNum > 1) {
                    gamePanel.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandNum == 0) {
                    gamePanel.ui.storeScreenState = 1;
                    gamePanel.player.hasMoney -= gamePanel.store[i].cost;
                    gamePanel.player.enterStore++;
                }
                if (gamePanel.ui.commandNum == 1) {
                    gamePanel.gameState = gamePanel.playState;
                    gamePanel.ui.commandNum = 0;
                }


            }
        } else if (gamePanel.ui.storeScreenState == 1) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.ui.commandNum = 0;
                gamePanel.ui.storeScreenState = 0;

            }
        }

    }


    ///////////////////// Fake TextField Iput Methods //////////////////
    private void inputToName(int code) {

        switch (code) {
            case KeyEvent.VK_A:
                gamePanel.player.addPlayerName("a");
                break;
            case KeyEvent.VK_B:
                gamePanel.player.addPlayerName("b");
                break;
            case KeyEvent.VK_C:
                gamePanel.player.addPlayerName("c");
                break;
            case KeyEvent.VK_D:
                gamePanel.player.addPlayerName("d");
                break;
            case KeyEvent.VK_E:
                gamePanel.player.addPlayerName("e");
                break;
            case KeyEvent.VK_F:
                gamePanel.player.addPlayerName("f");
                break;
            case KeyEvent.VK_G:
                gamePanel.player.addPlayerName("g");
                break;
            case KeyEvent.VK_H:
                gamePanel.player.addPlayerName("h");
                break;
            case KeyEvent.VK_I:
                gamePanel.player.addPlayerName("i");
                break;
            case KeyEvent.VK_J:
                gamePanel.player.addPlayerName("j");
                break;
            case KeyEvent.VK_K:
                gamePanel.player.addPlayerName("k");
                break;
            case KeyEvent.VK_L:
                gamePanel.player.addPlayerName("l");
                break;
            case KeyEvent.VK_M:
                gamePanel.player.addPlayerName("m");
                break;
            case KeyEvent.VK_N:
                gamePanel.player.addPlayerName("n");
                break;
            case KeyEvent.VK_O:
                gamePanel.player.addPlayerName("o");
                break;
            case KeyEvent.VK_P:
                gamePanel.player.addPlayerName("p");
                break;
            case KeyEvent.VK_Q:
                gamePanel.player.addPlayerName("q");
                break;
            case KeyEvent.VK_R:
                gamePanel.player.addPlayerName("r");
                break;
            case KeyEvent.VK_S:
                gamePanel.player.addPlayerName("s");
                break;
            case KeyEvent.VK_T:
                gamePanel.player.addPlayerName("t");
                break;
            case KeyEvent.VK_U:
                gamePanel.player.addPlayerName("u");
                break;
            case KeyEvent.VK_V:
                gamePanel.player.addPlayerName("v");
                break;
            case KeyEvent.VK_W:
                gamePanel.player.addPlayerName("w");
                break;
            case KeyEvent.VK_X:
                gamePanel.player.addPlayerName("x");
                break;
            case KeyEvent.VK_Y:
                gamePanel.player.addPlayerName("y");
                break;
            case KeyEvent.VK_Z:
                gamePanel.player.addPlayerName("z");
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.player.addPlayerName(" ");
                break;
            case KeyEvent.VK_0:
                gamePanel.player.addPlayerName("0");
                break;
            case KeyEvent.VK_1:
                gamePanel.player.addPlayerName("1");
                break;
            case KeyEvent.VK_2:
                gamePanel.player.addPlayerName("2");
                break;
            case KeyEvent.VK_3:
                gamePanel.player.addPlayerName("3");
                break;
            case KeyEvent.VK_4:
                gamePanel.player.addPlayerName("4");
                break;
            case KeyEvent.VK_5:
                gamePanel.player.addPlayerName("5");
                break;
            case KeyEvent.VK_6:
                gamePanel.player.addPlayerName("6");
                break;
            case KeyEvent.VK_7:
                gamePanel.player.addPlayerName("7");
                break;
            case KeyEvent.VK_8:
                gamePanel.player.addPlayerName("8");
                break;
            case KeyEvent.VK_9:
                gamePanel.player.addPlayerName("9");
                break;
            case KeyEvent.VK_TAB:
                gamePanel.player.addPlayerName("\t");
                break;
            case KeyEvent.VK_BACK_SPACE:
                gamePanel.player.backSpaceName();
                break;
			/*case KeyEvent.VK_ADD: gamePanel.player.addPlayerName("+"); break;
			case KeyEvent.VK_ASTERISK: gamePanel.player.addPlayerName("*"); break;
			case KeyEvent.VK_BRACELEFT: gamePanel.player.addPlayerName("{"); break;
			case KeyEvent.VK_BRACERIGHT: gamePanel.player.addPlayerName("}"); break;
			case KeyEvent.VK_DECIMAL: gamePanel.player.addPlayerName("."); break;
			case KeyEvent.VK_DIVIDE: gamePanel.player.addPlayerName("/"); break;
			case KeyEvent.VK_DOLLAR: gamePanel.player.addPlayerName("$"); break;*/

            default:
                break;
        }
    }


}
