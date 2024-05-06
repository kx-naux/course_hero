<style>
    .add-button {
        margin-top: 5px;
        position: relative;
        width: 150px;
        height: 40px;
        cursor: pointer;
        display: flex;
        align-items: center;
        border: 1px solid var(--color-primary);
        background-color: var(--color-primary);
        border-radius: 4px;
    }

    .add-button, .add-button__icon, .add-button__text {
        transition: all 0.5s;
    }

    .add-button .add-button__text {
        transform: translateX(30px);
        color: #fff;
        font-weight: 600;
    }

    .add-button .add-button__icon {
        position: absolute;
        transform: translateX(109px);
        height: 100%;
        width: 39px;
        background-color: #636dcc;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 4px;
    }

    .add-button .svg {
        width: 30px;
        stroke: #fff;
    }

    .add-button:hover {
        background: var(--color-primary);
    }

    .add-button:hover .add-button__text {
        color: transparent;
    }

    .add-button:hover .add-button__icon {
        width: 148px;
        transform: translateX(0);
    }

    .add-button:active .add-button__icon {
        background-color: var(--color-primary);
    }

    .add-button:active {
        border: 1px solid #636dcc;
    }
</style>
<button type="button" class="add-button">
  <span class="add-button__text">Add Item</span>
  <span class="add-button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor" height="24" fill="none" class="svg"><line y2="19" y1="5" x2="12" x1="12"></line><line y2="12" y1="12" x2="19" x1="5"></line></svg></span>
</button>