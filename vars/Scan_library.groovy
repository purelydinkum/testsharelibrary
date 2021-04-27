def call(String Git_url,
         String Git_credentials_Id='admin',
         String Param_Type='1',
         String Default_Scan_Bat_Path='default',
         String Additional='default') {
    String Scan_Version = ''
    String Scan_Type = ''
    String Tag_Msg = ''
    String DDSC_Tool_Path = 'D:\\JenkinsTool\\DDSC_Git\\DDSC_Git.exe'
    String Scan_Run_Bat_Path = 'D:\\JenkinsTool\\DDSC_Git\\Fortify_Default.bat'
    String Diff_Report_Path = ''
    String Diff_Files_Path = ''

    if(Param_Type == '1'){
        properties([
            parameters([
                gitParameter( name: 'Git_Version', 
                     type: 'PT_REVISION',
                     branch: '',
                     defaultValue: 'master',
                     selectedValue: 'NONE',
                     sortMode: 'NONE',
                     description: 'Select Git Version',
                     quickFilterEnabled: true,
                     useRepository: Git_url),
                
                string (name: 'Git_CompareVersion', 
                       defaultValue: 'HEAD~1', 
                       description: 'Can use branch, tag, commitSHA or HEAD~n.'),
                
                string (name: 'Scan_Bat_Path', 
                       defaultValue: Default_Scan_Bat_Path, 
                       description: 'Fortify command path.'),
                
                choice (name: 'Scan_Type',
                       choices: ['1.Generate diff report, and diff files scan.',
                                 '2.Generate diff report, and all files scan.',
                                 '3.Only generate diff report.'],
                       description: '')
            ])
        ])
    }


    pipeline {
        agent any
        stages {
            stage('Set_Parameter') {
                steps {
                    script {
                            bat """
                                @ECHO OFF
                                echo Set_Parameter
                            """
                            if (Scan_Bat_Path != 'default'){
                                Scan_Run_Bat_Path = "${WORKSPACE}\\${Scan_Bat_Path}"
                            }
                        }
                }
            }
            stage('Get_Parameter') {
                steps {
                    script {
                            bat """
                                @ECHO OFF
                                echo Parameter List
                                echo Scan_Run_Bat_Path : ${Scan_Run_Bat_Path}
                                echo Scan_Type : ${params.Scan_Type}
                            """
                        }
                }
            }
        }
    }

}
