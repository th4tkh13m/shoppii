import SideBar from '../SideBar'

function PrivateLayout({ children }) {
    return (
        <div>
            <SideBar />
            <div className="content">{children}</div>
        </div>
    )
}

export default PrivateLayout
