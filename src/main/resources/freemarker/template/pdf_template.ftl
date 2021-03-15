<!DOCTYPE html>
<html>
<head>
    <meta http-equiv=Content-Type content="text/html; charset=utf-8"/>
    <style>
 @page
	{size:21.0cm 842.0pt;
	}
    
    </style>
</head>

<body>

<div>
    <p align=center style='text-align:center;line-height:32.0pt;'>
        <span style='font-size:22.0pt;font-family:宋体;'>xxxxxxxx报名表</span>
    </p>

    <div align=center style='font-family:宋体;font-size:10.5pt'>
        <table border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;table-layout:fixed;' width="700px">
            <tr>
                <td colspan="3">
                    <p align="center">
                        <span>姓 名</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>${name}</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>性 别</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>${gender}</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>出生年月<br/>
                            （${age}岁）</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span style='font-family:宋体;'>${birthday}</span>
                    </p>
                </td>
                <td colspan="6" rowspan="3">
                    <p align="center">
                        <img style="width:80pt;height:120pt" src="${photo}" title=""/>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <p align="center">
                        <span>民 族</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>${nation}</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>籍 贯</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>${local}</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>政 治<br/>
                            面 貌</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>${political}</span>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <p align="center">
                        <span>健 康<br/>
                            状 况</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>${health}</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>婚 姻<br/>
                            状 况</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>${marriage}</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>技能</span>
                    </p>
                </td>
                <td colspan="3">
                    <p align="center">
                        <span>${position}</span>
                    </p>
                </td>
            </tr>

            <tr>
                <td colspan="3">
                    <p align="center">
                        <span style="writing-mode:tb-rl;">个人经历</span>
                    </p>
                </td>
                <td colspan="21" style="padding: 5px;">
                    <p align="left">
                        <span style="white-space:pre">${record}</span>
                    </p>
                </td>
            </tr>

        </table>
    </div>
</div>

</body>

</html>
