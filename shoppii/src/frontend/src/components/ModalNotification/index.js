import './index.css'

function ModalNotification() {
    return (
        <div class="success-checkmark d-flex flex-column justify-content-between align-items-center">
            <div class="check-icon">
                <span class="icon-line line-tip"></span>
                <span class="icon-line line-long"></span>
                <div class="icon-circle"></div>
                <div class="icon-fix"></div>
            </div>
            <h2
                style={{
                    marginTop: '20px',
                }}
            >
                Check
            </h2>
        </div>
    )
}

export default ModalNotification
