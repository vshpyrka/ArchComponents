/*
 * This file is generated by jOOQ.
*/
package db.jooq.arch.tables.records;


import db.jooq.arch.tables.PlayEvolutions;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlayEvolutionsRecord extends UpdatableRecordImpl<PlayEvolutionsRecord> implements Record7<Integer, String, LocalDateTime, String, String, String, String> {

    private static final long serialVersionUID = 838866568;

    /**
     * Setter for <code>public.play_evolutions.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.play_evolutions.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.play_evolutions.hash</code>.
     */
    public void setHash(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.play_evolutions.hash</code>.
     */
    public String getHash() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.play_evolutions.applied_at</code>.
     */
    public void setAppliedAt(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.play_evolutions.applied_at</code>.
     */
    public LocalDateTime getAppliedAt() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>public.play_evolutions.apply_script</code>.
     */
    public void setApplyScript(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.play_evolutions.apply_script</code>.
     */
    public String getApplyScript() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.play_evolutions.revert_script</code>.
     */
    public void setRevertScript(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.play_evolutions.revert_script</code>.
     */
    public String getRevertScript() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.play_evolutions.state</code>.
     */
    public void setState(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.play_evolutions.state</code>.
     */
    public String getState() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.play_evolutions.last_problem</code>.
     */
    public void setLastProblem(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.play_evolutions.last_problem</code>.
     */
    public String getLastProblem() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, LocalDateTime, String, String, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, LocalDateTime, String, String, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return PlayEvolutions.PLAY_EVOLUTIONS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return PlayEvolutions.PLAY_EVOLUTIONS.HASH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field3() {
        return PlayEvolutions.PLAY_EVOLUTIONS.APPLIED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return PlayEvolutions.PLAY_EVOLUTIONS.APPLY_SCRIPT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return PlayEvolutions.PLAY_EVOLUTIONS.REVERT_SCRIPT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return PlayEvolutions.PLAY_EVOLUTIONS.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return PlayEvolutions.PLAY_EVOLUTIONS.LAST_PROBLEM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getHash();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component3() {
        return getAppliedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getApplyScript();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getRevertScript();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getLastProblem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getHash();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value3() {
        return getAppliedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getApplyScript();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getRevertScript();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getLastProblem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayEvolutionsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayEvolutionsRecord value2(String value) {
        setHash(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayEvolutionsRecord value3(LocalDateTime value) {
        setAppliedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayEvolutionsRecord value4(String value) {
        setApplyScript(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayEvolutionsRecord value5(String value) {
        setRevertScript(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayEvolutionsRecord value6(String value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayEvolutionsRecord value7(String value) {
        setLastProblem(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayEvolutionsRecord values(Integer value1, String value2, LocalDateTime value3, String value4, String value5, String value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlayEvolutionsRecord
     */
    public PlayEvolutionsRecord() {
        super(PlayEvolutions.PLAY_EVOLUTIONS);
    }

    /**
     * Create a detached, initialised PlayEvolutionsRecord
     */
    public PlayEvolutionsRecord(Integer id, String hash, LocalDateTime appliedAt, String applyScript, String revertScript, String state, String lastProblem) {
        super(PlayEvolutions.PLAY_EVOLUTIONS);

        set(0, id);
        set(1, hash);
        set(2, appliedAt);
        set(3, applyScript);
        set(4, revertScript);
        set(5, state);
        set(6, lastProblem);
    }
}
