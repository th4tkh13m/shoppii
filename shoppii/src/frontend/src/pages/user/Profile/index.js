import React from 'react'
import ProfileBox from './components/ProfileBox'
import ProfileSideBar from './components/ProfileSidebar'
import { editPro } from '../Profile/components/ProfileSidebar'

function Profile() {
    const [content, setContent] = React.useState(editPro)

    const getActionFromSidebar = action => setContent(action)

    return (
        <main
            className="container d-flex justify-content-between h-auto"
            style={{
                padding: '30px 0 30px 0',
                margin: 0,
                backgroundColor: '#fafafa',
            }}
        >
            <ProfileSideBar getAction={getActionFromSidebar} />
            <ProfileBox content={content} />
        </main>
    )
}

export default Profile
