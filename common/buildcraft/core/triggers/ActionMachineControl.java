package buildcraft.core.triggers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Icon;
import buildcraft.BuildCraftTransport;
import buildcraft.api.gates.Action;
import buildcraft.core.DefaultProps;
import buildcraft.transport.IconItemConstants;

public class ActionMachineControl extends Action {

	public enum Mode {
		Unknown, On, Off, Loop
	};

	Mode mode;

	public ActionMachineControl(int id, Mode mode) {
		super(id);

		this.mode = mode;
	}

	@Override
	public String getDescription() {
		switch (mode) {
		case On:
			return "On";
		case Off:
			return "Off";
		case Loop:
			return "Loop";
		default:
			return "";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getTexture() {
		switch (mode) {
		case On:
			return BuildCraftTransport.instance.itemIcons[IconItemConstants.Action_MachineControl_On];
		case Off:
			return BuildCraftTransport.instance.itemIcons[IconItemConstants.Action_MachineControl_Off];
		case Loop:
			return BuildCraftTransport.instance.itemIcons[IconItemConstants.Action_MachineControl_Loop];
		default:
			return null;
		}
	}

}
