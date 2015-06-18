/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL. Please check the contents
 * of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt */
package buildcraft.core.builders.patterns;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import buildcraft.api.blueprints.SchematicMask;
import buildcraft.api.enums.EnumFillerPattern;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.core.Box;
import buildcraft.core.blueprints.BptBuilderTemplate;
import buildcraft.core.blueprints.Template;

public class PatternFlatten extends FillerPattern {

    public PatternFlatten() {
        super("flatten", EnumFillerPattern.FLATTEN);
    }

    @Override
    public Template getTemplate(Box box, World world, IStatementParameter[] parameters) {
        int xMin = (int) box.pMin().xCoord;
        int yMin = box.pMin().yCoord > 0 ? (int) box.pMin().yCoord - 1 : 0;
        int zMin = (int) box.pMin().zCoord;

        int xMax = (int) box.pMax().xCoord;
        int yMax = (int) box.pMax().yCoord;
        int zMax = (int) box.pMax().zCoord;

        Template bpt = new Template(box.sizeX(), yMax - yMin + 1, box.sizeZ());

        if (box.pMin().yCoord > 0) {
            for (int x = xMin; x <= xMax; ++x) {
                for (int z = zMin; z <= zMax; ++z) {
                    bpt.contents[x - xMin][0][z - zMin] = new SchematicMask(true);
                }
            }
        }

        return bpt;
    }

    @Override
    public BptBuilderTemplate getTemplateBuilder(Box box, World world, IStatementParameter[] parameters) {
        int yMin = box.pMin().yCoord > 0 ? (int) box.pMin().yCoord - 1 : 0;

        return new BptBuilderTemplate(getTemplate(box, world, parameters), world, new BlockPos(box.xMin, yMin, box.zMin));
    }
}
