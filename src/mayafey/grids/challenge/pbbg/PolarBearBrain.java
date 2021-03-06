package mayafey.grids.challenge.pbbg;

import mayafey.grids.data.GridView;

public abstract class PolarBearBrain {
	
	public static final int VISION = 0;
	public static final int RESISTANCE = 1;
	public static final int ATTACK = 2;
	public static final int DIGEFF = 3;
	public static final int RESPEFF = 4;

	protected PolarBearAccessObject access;
	
	/**
	 * The type of the attacker/defender is given, return true to attack
	 * or false to defend. Attacking means you deal more damage but suffer 
	 * enemy resistance damage.
	 */
	public abstract boolean defendAgainst(String type);
	
	/**
	 * In this method you must return the <b>direction</b> you want to move. 
	 * Use the static methods in PositionGrid to help you.<br><br>
	 * 
	 * To use the grid view:<br><br>
	 * <ul>
	 * 	<li>
	 * 	  <b>view.getGivenSize()</b>:<br>
	 * 	  Returns the amount of animals in the current view.
	 *  </li>
	 *  <li>
	 * 	  <b>view.getGetPos(pos, xy)</b>:<br>
	 * 	  Returns the grid position of the animal specified. Will silently fail
	 *    if you pass it a position out of range, and will throw an exception
	 *    if you pass it a position outside of its array. Returns the position
	 *    in the array <u>xy</u>.
	 *  </li>
	 *  <li>
	 * 	  <b>view.getX(pos)</b>:<br>
	 * 	  returns the X position of the animal specified. Like getPos will silently 
	 *    fail if position is out of GivenSize.
	 *  </li>
	 *  <li>
	 * 	  <b>view.getY(pos)</b>:<br>
	 * 	  Returns the Y position of the animal specified. Like getPos will silently 
	 *    fail if position is out of GivenSize.
	 *  </li>
	 *  <li>
	 * 	  <b>view.getObj(pos)</b>:<br>
	 * 	  Returns the type of the animal specified. Like getPos will silently 
	 *    fail if position is out of GivenSize. If the type is unknown, will return 
	 *    "Animal"
	 *  </li>
	 * </ul><br><br>
	 * 
	 * To use the access object:<br><br>
	 * <ul>
	 *  <li>
	 *   <b>access.directionTo(x, y)</b>:<br>
	 *   Returns the direction that will get to the specified position the fastest.
	 *  </li>
	 *  <li>
	 *   <b>access.distanceTo(x, y)</b>:<br>
	 *   Returns the distance to the given position given assuming the most ideal
	 *   route is taken.
	 *  </li>
	 *  <li>
	 *   <b>access.getAttack()</b>:<br>
	 *   Returns this bears skill in attack.
	 *  </li>
	 *  <li>
	 *   <b>access.getResistance()</b>:<br>
	 *   Returns this bears skill in resistance.
	 *  </li>
	 *  <li>
	 *   <b>access.getVision()</b>:<br>
	 *   Returns this bears skill in vision.
	 *  </li>
	 *  <li>
	 *   <b>access.getDigestiveEfficiency()</b>:<br>
	 *   Returns this bears skill in Digestive Efficiency.
	 *  </li>
	 *  <li>
	 *   <b>access.getRespiratoryEfficiency()</b>:<br>
	 *   Returns this bears skill in Respiratory Efficiency.
	 *  </li>
	 *  <li>
	 *   <b>access.getWeight()</b>:<br>
	 *   Returns the current weight.
	 *  </li>
	 *  <li>
	 *   <b>access.getHealth()</b>:<br>
	 *   Returns the current health.
	 *  </li>
	 *  <li>
	 *   <b>access.getMaxHealth()</b>:<br>
	 *   Returns the current maximum health.
	 *  </li>
	 *  <li>
	 *   <b>access.getX()</b>:<br>
	 *   Returns the current X-Position of the bear.
	 *  </li>
	 *  <li>
	 *   <b>access.getY()</b>:<br>
	 *   Returns the current Y-Position of the bear.
	 *  </li>
	 *  <li>
	 *   <b>access.getRandom()</b>:<br>
	 *   Returns the RNG for the current game.
	 *  </li>
	 * </ul><br>
	 */
	public abstract int move(GridView<String> view);
	
	/**
	 * <ul>
	 * <li>0 = Vision</li>
	 * <li>1 = Resistance</li>
	 * <li>2 = Attack</li>
	 * <li>3 = Digestive Efficiency</li>
	 * <li>4 = Respiratory Efficiency</li>
	 * </ul><br>
	 * 
	 * <b>Vision</b> provides you the tactical information each round. This comes
	 * as a list of animals in the radius of your animal. Increased vision increases
	 * the radius and increases the probability of determining the animal type. Default
	 * radius is 0 - 2 squares in all directions and default probability is 0% of 
	 * determining animal type.<br>
	 * <b>Resistance</b> decreases damage dealt from enemy animals by -1%, increases 
	 * defensive damage dealt to them by on average 0.5 points, and increases the 
	 * maximum HP of your bear by 1 per point<br>
	 * <b>Attack</b> increases the amount of damage you do on top of the weight damage.
	 * An increase in attack provides a flat increase in damage plus a +1% modifier on
	 * the weight damage.<br>
	 * <b>Digestive Efficiency</b> increases the amount of weight gained by eating food.
	 * Each additional point gives 1% of the weight from food. Default efficiency is 
	 * 20% - 50%<br>
	 * <b>Respiratory Efficiency</b> decreases the amount of weight lost to attrition
	 * each tick of the game and increases regeneration. Each point reduces attrition by
	 * -1% and every two points increases regeneration by 1 point per round. Default 
	 * regeneration is 5 points plus an additional 0 - 5 points if you've recently eaten.
	 * The 'recently eaten' period lasts for one turn plus an average of 0.25 turns for every
	 * point in respiratory efficiency.<br><br>
	 * Scores must not have a sum greater then 50, and no score can be less then zero.<br>
	 */
	public abstract void getSkills(int[] arr, int weight);
	
	/**
	 * This method is called at the start of each tick, after regeneration but before
	 * moves.
	 */
	public abstract void tick();
	
	/**
	 * Called when this object receives its access object.
	 */
	protected abstract void init();
	
	final void setAccess(PolarBearAccessObject access)
	{
		this.access = access;
		init();
	}
	
	protected final PolarBearAccessObject getAccess()
	{
		return this.access;
	}
}
