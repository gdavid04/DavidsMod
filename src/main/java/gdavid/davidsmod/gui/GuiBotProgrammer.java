package gdavid.davidsmod.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import gdavid.davidsmod.DavidsMod;
import gdavid.davidsmod.api.DavidsModRegistries;
import gdavid.davidsmod.api.bots.Piece;
import gdavid.davidsmod.api.bots.PieceCategory;
import gdavid.davidsmod.api.bots.PieceMod;
import gdavid.davidsmod.api.bots.Program;
import gdavid.davidsmod.item.ModItems;
import gdavid.davidsmod.network.PacketHandler;
import gdavid.davidsmod.network.message.PacketBotProgrammerSetPiece;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

public class GuiBotProgrammer extends GuiScreen {
	
	static final ResourceLocation tex = new ResourceLocation(DavidsMod.modID, "textures/gui/bot_programmer.png");
	
	int w, h, x, y;
	
	final EntityPlayer player;
	final EnumHand hand;
	Program program;
	
	boolean selected = false;
	int selected_process, selected_step, selected_mod;
	boolean hover;
	int hover_process, hover_step, hover_mod;
	
	int tab_page = 0;
	PieceCategory tab;
	int catalog_page = 0;
	
	List<PieceCategory> tabs = DavidsModRegistries.pieceCategory.getValues();
	int tab_page_count = tabs.size() / tab_count + (tabs.size() % tab_count == 0 ? 0 : 1);
	int catalog_page_count;
	
	public GuiBotProgrammer(EntityPlayer player, EnumHand hand) {
		this.player = player;
		this.hand = hand;
		ItemStack stack = player.getHeldItem(hand);
		if (stack.hasTagCompound()) {
			program = Program.fromNbt(stack.getTagCompound().getCompoundTag("program"));
		} else {
			program = new Program();
		}
		tab = tabs.get(0);
		catalog_page_count = (tab.pieces.size() + tab.mods.size()) / (catalog_w * catalog_h) + ((tab.pieces.size() + tab.mods.size()) % (catalog_w * catalog_h) == 0 ? 0 : 1);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void updateScreen() {
		if (Calendar.getInstance().get(Calendar.MONTH) == Calendar.APRIL && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) {
			fontRenderer = mc.standardGalacticFontRenderer;
		}
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getItem() != ModItems.botProgrammer) {
			this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
		}
		if (stack.hasTagCompound()) {
			program = Program.fromNbt(stack.getTagCompound().getCompoundTag("program"));
		} else {
			program = new Program();
		}
	}
	
	@Override
	public void initGui() {
		w = 191;
		h = 155;
		x = (width - w) / 2;
		y = (height - h) / 2;
	}
	
	private static final int PIECE = -1;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int mx = mouseX - this.x;
		int my = mouseY - this.y;
		int x, y;
		hover = false;
		if (inside(mx, my, 6, 6, 114, 148)) {
			hover_pos:
			for (int process = 0; process < Program.processCount; process++) {
				for (int step = 0; step < Program.stepCount; step++) {
					if (insidePiece(mx, my, process, step)) {
						hover = true;
						hover_process = process;
						hover_step = step;
						hover_mod = PIECE;
						break hover_pos;
					}
					for (int mod = 0; mod < Program.modCount; mod++) {
						if (insideMod(mx, my, process, step, mod)) {
							hover = true;
							hover_process = process;
							hover_step = step;
							hover_mod = mod;
							break hover_pos;
						}
					}
				}
			}
		}
		drawDefaultBackground();
		GlStateManager.color(1F, 1F, 1F);
		mc.getTextureManager().bindTexture(tex);
		drawTexturedModalRect(this.x, this.y, 0, 0, w, h);
		for (int process = 0; process < Program.processCount; process++) {
			for (int step = 0; step < Program.stepCount; step++) {
				x = piece_x(process, step) + this.x;
				y = piece_y(process, step) + this.y;
				Piece piece = program.pieces[process][step];
				if (piece != null) {
					if (DavidsModRegistries.piece.containsValue(piece)) {
						mc.getTextureManager().bindTexture(piece.getTextureLocation());
						ScaledDrawFull(x, y, piece_w, piece_h);
						mc.getTextureManager().bindTexture(tex);
					} else {
						drawTexturedModalRect(x, y, unknown_piece_x, unknown_piece_y, piece_w, piece_h);
					}
				}
				if (
					hover &&
					hover_process == process &&
					hover_step == step &&
					hover_mod == PIECE
				) {
					drawTexturedModalRect(x - 1, y - 1, hover_piece_x, hover_piece_y, piece_w + 2, piece_h + 2);
				}
				if (
					selected &&
					selected_process == process &&
					selected_step == step &&
					selected_mod == PIECE
				) {
					drawTexturedModalRect(x - 1, y - 1, selected_piece_x, selected_piece_y, piece_w + 2, piece_h + 2);
				}
				for (int mod = 0; mod < Program.modCount; mod++) {
					x = mod_x(process, step, mod) + this.x;
					y = mod_y(process, step, mod) + this.y;
					PieceMod piecemod = program.mods[process][step][mod];
					if (piecemod != null) {
						if (DavidsModRegistries.pieceMod.containsValue(piecemod)) {
							mc.getTextureManager().bindTexture(piecemod.getTextureLocation());
							ScaledDrawFull(x, y, mod_w, mod_h);
							mc.getTextureManager().bindTexture(tex);
						} else {
							drawTexturedModalRect(x, y, unknown_mod_x, unknown_mod_y, mod_w, mod_h);
						}
					}
					if (
						hover &&
						hover_process == process &&
						hover_step == step &&
						hover_mod == mod
					) {
						drawTexturedModalRect(x - 1, y - 1, hover_mod_x, hover_mod_y, mod_w + 2, mod_h + 2);
					}
					if (
						selected &&
						selected_process == process &&
						selected_step == step &&
						selected_mod == mod
					) {
						drawTexturedModalRect(x - 1, y - 1, selected_mod_x, selected_mod_y, mod_w + 2, mod_h + 2);
					}
				}
			}
		}
		if (tab_page > 0) {
			drawTexturedModalRect(
				tab_scroll_px + this.x, tab_scroll_py_up + this.y,
				tab_scroll_x, tab_scroll_y + (
					inside(mx, my, tab_scroll_px, tab_scroll_py_up, tab_scroll_w, tab_scroll_h) ?
					tab_scroll_h : 0
				),
				tab_scroll_w, tab_scroll_h
			);
		}
		if (tab_page < tab_page_count - 1) {
			drawTexturedModalRect(
				tab_scroll_px + this.x, tab_scroll_py_down + this.y,
				tab_scroll_x + tab_scroll_w, tab_scroll_y + (
					inside(mx, my, tab_scroll_px, tab_scroll_py_down, tab_scroll_w, tab_scroll_h) ?
					tab_scroll_h : 0
				),
				tab_scroll_w, tab_scroll_h
			);
		}
		boolean hover_page = false;
		for (int t = 0; t < tab_count && tab_page * tab_count + t < tabs.size(); t++) {
			PieceCategory ctab = tabs.get(tab_page * tab_count + t);
			int ty = tab_y;
			if (inside(mx, my, tabs_x, tabs_y + t * tab_h, tab_w, tab_h) && !hover_page) {
				ty = tab_hover_y;
				hover_page = true;
			}
			if (tab == ctab) {
				ty = tab_active_y;
			}
			drawTexturedModalRect(
				tabs_x + this.x, tabs_y + this.y + tab_h * t,
				tab_x, ty,
				tab_w, tab_h
			);
			mc.getTextureManager().bindTexture(ctab.getTextureLocation());
			ScaledDrawFull(
				tabs_x + this.x + (tab == ctab ? tab_active_icon_x : tab_icon_x), tabs_y + this.y + tab_h * t + tab_icon_y,
				tab_icon_w, tab_icon_h
			);
			mc.getTextureManager().bindTexture(tex);
		}
		if (catalog_page > 0) {
			drawTexturedModalRect(
				catalog_scroll_left + this.x, catalog_scroll_oy + this.y,
				catalog_scroll_xl, catalog_scroll_y + (
					inside(mx, my, catalog_scroll_left, catalog_scroll_oy, catalog_scroll_w, catalog_scroll_h) ?
					catalog_scroll_h : 0
				),
				catalog_scroll_w, catalog_scroll_h
			);
		}
		if (catalog_page < catalog_page_count - 1) {
			drawTexturedModalRect(
				catalog_scroll_right + this.x, catalog_scroll_oy + this.y,
				catalog_scroll_xr, catalog_scroll_y + (
					inside(mx, my, catalog_scroll_right, catalog_scroll_oy, catalog_scroll_w, catalog_scroll_h) ?
					catalog_scroll_h : 0
				),
				catalog_scroll_w, catalog_scroll_h
			);
		}
		catalog:
		for (int cy = 0; cy < catalog_h; cy++) {
			for (int cx = 0; cx < catalog_w; cx++) {
				int n = cx + cy * catalog_w + catalog_page * catalog_w * catalog_h;
				if (n >= tab.pieces.size() + tab.mods.size()) {
					break catalog;
				}
				if (n >= tab.pieces.size()) {
					mc.getTextureManager().bindTexture(tab.mods.get(n - tab.pieces.size()).getTextureLocation());
					ScaledDrawFull(
						catalog_mod_ox + catalog_x + (piece_w + 1) * cx + this.x, catalog_mod_oy + catalog_y + (piece_h + 1) * cy + this.y,
						mod_w, mod_h
					);
					mc.getTextureManager().bindTexture(tex);
					if (inside(mx, my, catalog_mod_ox + catalog_x + (piece_w + 1) * cx, catalog_mod_oy + catalog_y + (piece_h + 1) * cy, mod_w, mod_h)) {
						drawTexturedModalRect(
							catalog_mod_ox + catalog_x + (piece_w + 1) * cx + this.x - 1, catalog_mod_oy + catalog_y + (piece_h + 1) * cy + this.y - 1,
							hover_mod_x, hover_mod_y, mod_w + 2, mod_h + 2
						);
					}
				} else {
					mc.getTextureManager().bindTexture(tab.pieces.get(n).getTextureLocation());
					ScaledDrawFull(
						catalog_x + (piece_w + 1) * cx + this.x, catalog_y + (piece_h + 1) * cy + this.y,
						piece_w, piece_h
					);
					mc.getTextureManager().bindTexture(tex);
					if (inside(mx, my, catalog_x + (piece_w + 1) * cx, catalog_y + (piece_h + 1) * cy, piece_w, piece_h)) {
						drawTexturedModalRect(
							catalog_x + (piece_w + 1) * cx + this.x - 1, catalog_y + (piece_h + 1) * cy + this.y - 1,
							hover_piece_x, hover_piece_y, piece_w + 2, piece_h + 2
						);
					}
				}
			}
		}
		if (hover && isShiftKeyDown()) {
			if (hover_mod == PIECE) {
				Piece piece = program.pieces[hover_process][hover_step];
				if (piece != null) {
					drawHoveringText(Arrays.asList(piece.getDescription().getFormattedText().split("\n")), mouseX, mouseY);
				}
			} else {
				PieceMod piecemod = program.mods[hover_process][hover_step][hover_mod];
				if (piecemod != null) {
					drawHoveringText(Arrays.asList(piecemod.getDescription().getFormattedText().split("\n")), mouseX, mouseY);
				}
			}
		}
		for (int t = 0; t < tab_count && tab_page * tab_count + t < tabs.size(); t++) {
			PieceCategory ctab = tabs.get(tab_page * tab_count + t);
			if (inside(mx, my, tabs_x, tabs_y + t * tab_h, tab_w, tab_h)) {
				drawHoveringText(Arrays.asList(ctab.getDescription().getFormattedText().split("\n")), mouseX, mouseY);
				break;
			}
		}
		catalog:
		for (int cy = 0; cy < catalog_h; cy++) {
			for (int cx = 0; cx < catalog_w; cx++) {
				int n = cx + cy * catalog_w + catalog_page * catalog_w * catalog_h;
				if (n >= tab.pieces.size() + tab.mods.size()) {
					break catalog;
				}
				if (n >= tab.pieces.size()) {
					if (inside(mx, my, catalog_mod_ox + catalog_x + (piece_w + 1) * cx, catalog_mod_oy + catalog_y + (piece_h + 1) * cy, mod_w, mod_h)) {
						PieceMod piecemod = tab.mods.get(n - tab.pieces.size());
						drawHoveringText(Arrays.asList(piecemod.getDescription().getFormattedText().split("\n")), mouseX, mouseY);
						break catalog;
					}
				} else {
					if (inside(mx, my, catalog_x + (piece_w + 1) * cx, catalog_y + (piece_h + 1) * cy, piece_w, piece_h)) {
						Piece piece = tab.pieces.get(n);
						drawHoveringText(Arrays.asList(piece.getDescription().getFormattedText().split("\n")), mouseX, mouseY);
						break catalog;
					}
				}
			}
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		int x = mouseX - this.x;
		int y = mouseY - this.y;
		if (inside(x, y, 6, 6, 114, 148)) {
			selected = hover;
			selected_process = hover_process;
			selected_step = hover_step;
			selected_mod = hover_mod;
		}
		if (tab_page > 0 && inside(x, y, tab_scroll_px, tab_scroll_py_up, tab_scroll_w, tab_scroll_h)) {
			tab_page--;
		}
		if (tab_page < tab_page_count - 1 && inside(x, y, tab_scroll_px, tab_scroll_py_down, tab_scroll_w, tab_scroll_h)) {
			tab_page++;
		}
		for (int t = 0; t < tab_count && tab_page * tab_count + t < tabs.size(); t++) {
			if (inside(x, y, tabs_x, tabs_y + t * tab_h, tab_w, tab_h)) {
				tab = tabs.get(tab_page * tab_count + t);
				catalog_page = 0;
				catalog_page_count = (tab.pieces.size() + tab.mods.size()) / (catalog_w * catalog_h) + ((tab.pieces.size() + tab.mods.size()) % (catalog_w * catalog_h) == 0 ? 0 : 1);
				break;
			}
		}
		if (catalog_page > 0 && inside(x, y, catalog_scroll_left, catalog_scroll_oy, catalog_scroll_w, catalog_scroll_h)) {
			catalog_page--;
		}
		if (catalog_page < catalog_page_count - 1 && inside(x, y, catalog_scroll_right, catalog_scroll_oy, catalog_scroll_w, catalog_scroll_h)) {
			catalog_page++;
		}
		if (selected) {
			catalog:
			for (int cy = 0; cy < catalog_h; cy++) {
				for (int cx = 0; cx < catalog_w; cx++) {
					int n = cx + cy * catalog_w + catalog_page * catalog_w * catalog_h;
					if (n >= tab.pieces.size() + tab.mods.size()) {
						break catalog;
					}
					if (n >= tab.pieces.size()) {
						if (selected_mod == PIECE) {
							break catalog;
						}
						if (inside(x, y, catalog_mod_ox + catalog_x + (piece_w + 1) * cx, catalog_mod_oy + catalog_y + (piece_h + 1) * cy, mod_w, mod_h)) {
							setMod(selected_process, selected_step, selected_mod, tab.mods.get(n - tab.pieces.size()));
							break catalog;
						}
					} else {
						if (inside(x, y, catalog_x + (piece_w + 1) * cx, catalog_y + (piece_h + 1) * cy, piece_w, piece_h)) {
							if (selected_mod == PIECE) {
								setPiece(selected_process, selected_step, tab.pieces.get(n));
							}
							break catalog;
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 14 || keyCode == 211 && selected) {
			if (selected_mod == PIECE) {
				if (program.pieces[selected_process][selected_step] != null) {
					setPiece(selected_process, selected_step, null);
				}
			} else if (program.mods[selected_process][selected_step][selected_mod] != null) {
				setMod(selected_process, selected_step, selected_mod, null);
			}
		}
		super.keyTyped(typedChar, keyCode);
	}
	
	static boolean inside(int x, int y, int bx, int by, int w, int h) {
		return bx <= x && by <= y && x - bx <= w && y - by <= h;
	}
	
	static boolean insidePiece(int x, int y, int g, int n) {
		return inside(x, y, piece_x(g, n), piece_y(g, n), piece_w, piece_h);
	}
	
	static boolean insideMod(int x, int y, int g, int n, int s) {
		return inside(x, y, mod_x(g, n, s), mod_y(g, n, s), mod_w, mod_h);
	}
	
	static int piece_x(int process, int step) {
		return 6 + (piece_w + 1) * step;
	}
	
	static int piece_y(int process, int step) {
		return 6 + (piece_h + 1 + mod_h + 1) * process;
	}
	
	static int mod_x(int process, int step, int mod) {
		return piece_x(process, step) + mod * (mod_w + 1);
	}
	
	static int mod_y(int process, int step, int mod) {
		return piece_y(process, step) + piece_h + 1;
	}
	
	static final int piece_w = 21, piece_h = 24;
	static final int mod_w = 10, mod_h = 10;
	
	static final int selected_piece_x = 21, selected_piece_y = 155;
	static final int selected_mod_x = 77, selected_mod_y = 155;
	
	static final int hover_piece_x = 44, hover_piece_y = 155;
	static final int hover_mod_x = 89, hover_mod_y = 155;
	
	static final int unknown_piece_x = 0, unknown_piece_y = 155;
	static final int unknown_mod_x = 67, unknown_mod_y = 155;
	
	static final int tab_x = 0, tab_active_y = 181, tab_hover_y = 215, tab_y = 198;
	static final int tab_scroll_x = 113, tab_scroll_y = 155, tab_scroll_w = 10, tab_scroll_h = 6;
	static final int tab_scroll_px = 195, tab_scroll_py_up = 1, tab_scroll_py_down = 145;
	static final int tab_icon_x = 10, tab_active_icon_x = 19, tab_icon_y = 3, tab_icon_w = 11, tab_icon_h = 11;
	static final int tabs_x = 184, tabs_y = 8, tab_h = 17, tab_w = 33, tab_count = 8;
	
	static final int catalog_x = 118, catalog_y = 8;
	static final int catalog_w = 3, catalog_h = 5;
	static final int catalog_mod_ox = 5, catalog_mod_oy = 7;
	static final int catalog_scroll_left = 121, catalog_scroll_right = 172, catalog_scroll_oy = 135;
	static final int catalog_scroll_xl = 101, catalog_scroll_xr = 107, catalog_scroll_y = 155;
	static final int catalog_scroll_w = 6, catalog_scroll_h = 10;
	
	void setPiece(int process, int step, Piece piece) {
		PacketHandler.instance.sendToServer(new PacketBotProgrammerSetPiece(hand, process, step, piece == null ? "" : piece.getRegistryName().toString()));
	}
	
	void setMod(int process, int step, int mod, PieceMod piecemod) {
		PacketHandler.instance.sendToServer(new PacketBotProgrammerSetPiece(hand, process, step, mod, piecemod == null ? "" : piecemod.getRegistryName().toString()));
	}
	
	void ScaledDrawFull(int x, int y, int w, int h) {
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + h), (double)this.zLevel).tex(0, 1).endVertex();
        bufferbuilder.pos((double)(x + w), (double)(y + h), (double)this.zLevel).tex(1, 1).endVertex();
        bufferbuilder.pos((double)(x + w), (double)(y + 0), (double)this.zLevel).tex(1, 0).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex(0, 0).endVertex();
        tessellator.draw();
	}
	
}
